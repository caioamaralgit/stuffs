const routes = require('./routes.json');
import 'bootstrap';
import 'select2';
import 'select2/dist/css/select2.css'; 
import 'nouislider/distribute/nouislider.min.css';
import '../css/upload.css'; 
window.noUiSlider = require('nouislider');
window.PDFJS = require('pdfjs-dist/build/pdf');
window.Sortable = require('sortablejs');
window.wNumb = require('wnumb');

// Variable que guarda los datos de los archivos para upload
window.filesInfo = [];
window.thumbnails = [];
window.directoriesPath = {};

$(document).ready(() => {
    PDFJS.GlobalWorkerOptions.workerSrc = '../node_modules/pdfjs-dist/build/pdf.worker.min.js'; 

    // Eventos cuando el <input type="file"> del formato de upload tiene un cambio
    $("#form-upload-file").on("change", this, () => {    
        filesInfo = thumbnails = [];

        const files = Object.values($("#form-upload-file")[0].files);
        
        // Cambia el label arriba del <input type="file">
        let label = "";
        files.forEach((file, index) => label += `${index > 0 ? "," : ""} "${file.name}"`);
        $("label[for=form-upload-file]").text(files.length > 0  ? label : "Elegir archivo PDF");

        // Habilita o deshabilita el boton
        changeButton();
        
        function readFiles(files) {
            let reader = new FileReader();
            const file = files.shift();
    
            reader.readAsArrayBuffer(file);

            reader.onload = () => {
                generateThumb(new Uint8Array(reader.result));

                reader = new FileReader();
                reader.readAsBinaryString(file);
                reader.onloadend = () => {
                    filesInfo.push({ "name": file.name.substr(0, file.name.length - 4), 
                                    "pages": reader.result.match(/\/Type[\s]*\/Page[^s]/g).length, 
                                    "start": 1, 
                                    "end": reader.result.match(/\/Type[\s]*\/Page[^s]/g).length });

                    if (files.length > 0) readFiles(files);
                    else createFileList();
                }
            }
        }

        // Lee los archivos PDF y guarda las informaciones en la variable filesInfo
        if (files.length > 0) readFiles(files);
        else $("#filesList").html("<b>Tienes que seleccionar un archivo!</b>");
    });    

    // Cargar la lista de directorios
    $.ajax({
        url: `${routes["data-server"]}/files/directories?action=show`,
        headers: {
            'x-access-token': sessionStorage.getItem("token")
        },
        type: "GET",
        success: (data) => {
            let level = 0, results = [{ "id": JSON.parse(sessionStorage.getItem("usuario")).id_usuario, "level": 0, "text": "Crear en la raiz" }];

            function formatData(data, level, parentDir, callback) {
                data.forEach((directorio) => {
                    results.push({ "id": directorio.id_directorio, "level": level, "text": directorio.nombre });
                    
                    directoriesPath[directorio.id_directorio] = parentDir + directorio.nombre + "/";

                    if (directorio.subdirectorios.length > 0) formatData(directorio.subdirectorios, level + 1, directoriesPath[directorio.id_directorio]);
                });

                if (callback) callback();
            }

            function createSelect() {
                function formatResult(node) {
                    var $result = $(`<span style="padding-left: ${13 * node.level}px;">
                                        <img src="images/open-folder.png" class="select2-img" />
                                        ${node.text}
                                     </span>`);
                    return $result;
                };
            
                $("#form-upload-directorio").select2({
                    placeholder: 'Seleccione un directorio',
                    data: results,
                    formatSelection: (item) => { return item.text },
                    formatResult: (item) => { return item.text },
                    templateResult: formatResult
                });
            }

            formatData(data.directorios, level, "/", createSelect);
        }
    });
});

window.changeArray = (array, from, to) => {
    array.splice(to, 0, array.splice(from, 1)[0]);
    return array;
};

window.changeButton = () => {
    $(".custom-file-label[for=form-upload-file], #form-upload-nombre").removeClass("error");

    if ($("#form-upload-nombre").val().trim() !== "" && $("#form-upload-file")[0].files.length > 0) $("#modal-action").removeAttr("disabled");
    else $("#modal-action").attr("disabled", "disabled");
}

window.changeUploadScreen = (screen) => {
    $(".form-upload-screen").removeClass("active").eq(screen === "info" ? 0 : 1).addClass("active");

    if (screen === "info") {
        $("#form-upload-file-content").animate({ "margin-left": "100%", "margin-right": "-100%" }, 70, () => {
            $("#form-upload-file-content").hide(0, () => {
                $("#form-upload-file-info").show(0, () => {
                    $("#form-upload-file-info").animate({ "margin-left": 0, "margin-right": 0 }, 70);
                });
            });
        });
    } else {
        $("#form-upload-file-info").animate({ "margin-left": "-100%", "margin-right": "100%" }, 70, () => {
            $("#form-upload-file-info").hide(0, () => {
                $("#form-upload-file-content").show(0, () => {
                    $("#form-upload-file-content").animate({ "margin-left": 0, "margin-right": 0 }, 70);
                });
            });
        });
    }    
}

window.createFileList = () => {
    let items = [];

    filesInfo.forEach((file, index) => {
        const row = $(`<div class="row"></div>`);
        row.append($(`<div class="col-1 col-lg-1 sortable-handler" style="background-image: url('images/reorder-option.png');"></div>`));
        row.append($(`<div class="col-10 col-lg-5">${file.name}</div>`));
        row.append($(`<div class="col-12 col-lg-6"><div class="file-slider" data-max="${file.pages}"></div></div>`));

        items.push($(`<div class="list-group-item"></div>`).append(row));
        filesInfo[index].originalIndex = index;
    });

    $("#filesList").html("");

    if (filesInfo.length > 0) {
        $("#filesList").append(items);

        Sortable.create(filesList, {
            animation: 150,
            handle: '.sortable-handler',
            onUpdate: (evt) => filesInfo = changeArray(filesInfo, evt.oldIndex, evt.newIndex)
        });
        
        let sliderOptions = {
            start: [],
            connect: true,
            format: wNumb({ decimals: 0 }),
            tooltips: [true, true],
            range: { "min": 1 }
        };
    
        $(".file-slider").each((index, file) => {
            sliderOptions.start = [1, parseInt($(file).attr("data-max"))];
            sliderOptions.range.max = parseInt($(file).attr("data-max"));
    
            noUiSlider.create(file, sliderOptions); 
            file.noUiSlider.on("update", (values, handle) => {
                filesInfo.forEach((file, i) => {
                    if (file.originalIndex === index) filesInfo[i][handle === 0 ? "start" : "end"] = values[handle];
                });                
            });
        });

        changeUploadScreen("content");
    }
}

// Función creada por "async5" en https://stackoverflow.com/questions/44547585/generating-thumbnail-of-a-pdf-using-pdf-js
window.makeCanvas = (page) => {
    const vp = page.getViewport(1);
    const canvas = document.createElement("canvas");

    canvas.width = canvas.height = 100;

    const scale = Math.min(canvas.width / vp.width, canvas.height / vp.height);
    return page.render({canvasContext: canvas.getContext("2d"), viewport: page.getViewport(scale)}).promise.then(() => {
      return canvas;
    });
}

// Función creada por "async5" en https://stackoverflow.com/questions/44547585/generating-thumbnail-of-a-pdf-using-pdf-js
window.generateThumb = (file) => {
    PDFJS.getDocument(file).promise.then((pdf) => {
        return pdf.getPage(1).then(makeCanvas).then((canvas) => {
            console.log(canvas);
            thumbnails.push(canvas)
        });
    }).catch(console.error);
}

window.uploadFile = () => {
    if ($("#form-upload-nombre").val().trim() === "") {
        $("#form-upload-nombre").addClass("error");
        $("#form-upload-message").html("Tienes que poner un nombre para el archivo!");
        return;
    }

    if ($("#form-upload-file")[0].files.length === 0) {
        $(".custom-file-label[for=form-upload-file]").addClass("error");
        $("#form-upload-message").html("Tienes que seleccionar al menos un archivo!");
        return;
    }
    
    $("#modal-action").attr("disabled", "disabled");

    const form = new FormData();
    const files = $("#form-upload-file")[0].files;
    let filesInfoArray = []
    
    filesInfo.forEach((file) => {
        form.append('files', files[file.originalIndex]);        
        filesInfoArray.push({ start: parseInt(file.start), end: parseInt(file.end) })
    });

    form.append('filesInfo', JSON.stringify(filesInfoArray));
    form.append('fileDetails', JSON.stringify({ nombre: $("#form-upload-nombre").val(), 
                                                resumen: $("#form-upload-resumen").val(),
                                                directorio: directoriesPath[$("#form-upload-directorio").val()],
                                                id_directorio: $("#form-upload-directorio").val() }));

    $("#form-upload-message").html(`<img src="images/loading.gif" /> Subindo archivos... Por favor espere.`);

    $.ajax({
        url: `${routes["file-server"]}/upload`,
        headers: {
            'Authorization': `Bearer ${sessionStorage.getItem("file-token")}`,
            'x-access-token': sessionStorage.getItem("token")
        },
        type: "POST",
        processData: false,
        contentType: false,
        data: form,
        success: (data) => {
            console.log(data);
        }
    });
}