import React, { Component } from 'react';
import TableRows from './TableRows/TableRows'

class Table extends Component {

    render() {
        return (
            <table className="table table-hover">
                <thead>
                    <tr>
                        {this.props.table.columns.map(column => {
                            return <th scope="col" key={column.Field}>{column.Field}</th>;
                        })}
                    </tr>
                </thead>
                <tbody>
                    {this.props.table.data.map(row => {
                        return <tr key={row[Object.keys(row)[0]] + row[Object.keys(row)[1]]}><TableRows row = {row} /></tr>;
                    })}
                </tbody>
            </table>
        );
    }
}

export default Table;
