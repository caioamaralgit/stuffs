<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <title>Importação de arquivos</title>
    </head>
    <body>
        <h1>Importação de Arquivos</h1>
        <form action="/" method="POST" enctype="multipart/form-data">
            @csrf
            
            <label>Candidatos:</label>
            <input type="file" name="candidatos" /> <br />

            <label>Votos:</label>
            <input type="file" name="votos" /> <br />

            <br /><br />
            <button type="submit">Enviar</button>
        </form>
    </body>
</html>