import React, { Component } from 'react';

class TableRows extends Component {
    constructor() {
        super();
        this.state = {
            keys: []
        }

    }

    componentDidMount(){
        this.setState({
            keys: Object.keys(this.props.row)
        });
    }

    render() {
        let id;

        return (
            this.state.keys.map((key, index) => {
                if (index === 0) id = this.props.row[key];

                if (!this.props.hideColumns.includes(key))
                    return <td key={`${this.props.table}_${key}_${id}`}>{this.props.row[key]}</td>
            })
        );
    }
}

export default TableRows;
