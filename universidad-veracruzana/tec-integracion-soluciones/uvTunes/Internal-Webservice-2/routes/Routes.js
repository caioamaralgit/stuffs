module.exports = class Routes {
    static get banco() {
        return {
            "root": "http://localhost:8000/banco",
            "resources": {
                "transferir": "/"
            }
        }
    }
    
    static get hacienda() {
        return {
            "root": "http://148.226.80.106:8084/ServicioRestHaciendaldo/api/Hacienda",
            "resources": {
                "declarar": "/declarar",
                "facturar": "/facturar"
            }
        }
    }
    
    static get paqueteria() {
        return {
            "root": "http://148.226.81.33/PaqueteriaSW/api/paqueteria",
            "resources": {
                "buscar": "/busqueda",
                "consultar": "/consulta",
                "solicitar": "/envio"
            }
        }
    }

    static get proveedor() {
        return {
            "root": "http://localhost:8000/proveedor",
            "resources": {
                "comprar": "/comprar"
            }
        }
    }

    static getBanco(resource) { return `${this.banco.root}${this.banco.resources[resource]}`; }
    static getHacienda(resource) { return `${this.hacienda.root}${this.hacienda.resources[resource]}`; }
    static getPaqueteria(resource) { return `${this.paqueteria.root}${this.paqueteria.resources[resource]}`; }
    static getProveedor(resource) { return `${this.proveedor.root}${this.proveedor.resources[resource]}`; }    
}