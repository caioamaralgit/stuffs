import React, { Component } from 'react';

class TableRows extends Component {
    constructor(){
        super();
        this.state = {
            keys: []
        }
        this.objectToArray = this.objectToArray.bind(this);
    }
    componentDidMount(){
        this.setState({
            keys: Object.keys(this.props.row)
        });
    }

    objectToArray(){
    }

    render() {
        return (
            this.state.keys.map(key => {
                return <th key={key}>{this.props.row[key]}</th>
            })
        );
    }
}

export default TableRows;
