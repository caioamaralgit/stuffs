import React, { Component } from 'react';
import TableRows from './TableRows/TableRows'

class Table extends Component {
    constructor() {
        super();
        this.searchValue = this.searchValue.bind(this);
    }

    searchValue(rowObject) {
        if (this.props.search === null) return true;

        let contains = false;

        Object.values(rowObject).forEach((value) => {
            value = String(value);

            if (value.search(new RegExp(this.props.search, 'mgi')) > -1) contains = true;
        });

        return contains;
    }

    render() {
        let [thead, tbody] = [[], []];

        console.log("Table");
        console.log(this.props.data);

        if (this.props.data !== undefined) {
            this.props.data.columns.forEach(column => {
                if (!this.props.hideColumns.includes(column))
                    thead.push(<th scope="col" style={{textTransform : "capitalize"}} key={`${this.props.table}_${column}`}>{column.replace('_', ' ')}</th>)
            });

            Object.keys(this.props.data.rows).forEach((key) => {
                if (this.props.search === null || this.searchValue(this.props.data.rows[key]))
                    tbody.push(<tr key={`${this.props.table}_${key}`}><TableRows table = {this.props.table} row = {this.props.data.rows[key]} hideColumns={this.props.hideColumns} /></tr>)
            });
        }

        return (
            this.props.data !== undefined ?
                <table className="table table-hover">
                    <thead>
                        <tr>
                            {thead}
                        </tr>
                    </thead>
                    <tbody>
                        {tbody}
                    </tbody>
                </table>
            : [<br />, `No fue possible cargar los datos.`]
        );
    }
}

export default Table;
