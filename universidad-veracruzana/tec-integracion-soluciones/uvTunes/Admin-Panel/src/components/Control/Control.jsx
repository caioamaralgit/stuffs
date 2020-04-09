import React, { Component } from 'react';
import Table from '../Table/Table';
import { Input, InputGroup, InputGroupAddon } from 'reactstrap';
import './Control.css';

class Control extends Component {
    constructor(props) {
        super(props);
        this.state = {
            hideColumns: {
                productos: ["id_producto", "id_producto_proveedor", "id_productora", "id_proveedor", "descripcion", "ano", "foto", "peso", "permitir_consulta"],
                clientes: ["id_cliente", "fecha_nacimiento", "contrasena", "direccion", "cp", "ciudad", "estado", "token", "activado"],
                proveedores: ["id_proveedor"],
                administradores: ["id_administrador", "usuario", "contrasena"]
            }
        }
        
        this.props.setTable("productos");
    }

    render() {
        let buttons = <div id="action-buttons">
                            {(this.props.table === "productos" ? <button className="btn btn-primary">Productos Automatizados</button> : "")}
                            {(this.props.table !== "clientes" ? <button className="btn btn-primary">Nuevo Registro</button> : "")}
                        </div>
        return (
            <div className="col-sm">
                <header>
                    <div style={{ margin: "20px auto", display: "table", maxWidth: "450px", width: "100%"}}>
                        <InputGroup>
                            <InputGroupAddon addonType="prepend">Visualizar Tabla</InputGroupAddon>                            
                            <Input type="select" onChange={(e) => this.props.setTable(e.target.value)} ref={(input) => this.table = input}>
                                <option value="productos">Productos</option>
                                <option value="clientes">Clientes</option>
                                <option value="proveedores">Proveedores</option>
                                <option value="administradores">Administradores</option>
                            </Input>
                        </InputGroup>
                    </div>                        
                    <hr />
                    <h1 style={{textTransform:"capitalize"}}>{this.props.table}</h1>
                </header>
                <section className="table-responsive">
                    { buttons }
                    <div className="col table-container" style={{margin: "20px 0px"}}>
                        <br />
                        <Table table = {this.props.table} data = {this.props.data} search = {this.props.search} hideColumns = {this.state.hideColumns[this.props.table]} />
                    </div>
                </section>
            </div>
        );
    }
}

export default Control;
