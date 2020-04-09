import React, { Component } from 'react';
import Table from '../Table/Table';
import './Pedidos.css';

class Pedidos extends Component {
    constructor() {
        super();
        this.state = {
            hideColumns : ["id_cliente", "codigo_rastreo", "fecha_finalizacion", "articulo"]
        }
    }

    render() {
        return (
            <div className="col-sm 10">
                <header className="row justify-content-center">
                    <h1>Pedidos</h1>
                </header>
                <section className="table-responsive">
                    <div className="col table-container">
                        <Table table = {this.props.table} data = {this.props.data} search = {this.props.search} hideColumns = {this.state.hideColumns} />
                    </div>
                </section>
            </div>
        );
    }
}

export default Pedidos;