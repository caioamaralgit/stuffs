import React, { Component } from 'react';
import Table from './Table/Table';
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';
import './Panel.css';

class Panel extends Component {
    constructor(props) {
        super(props);
        this.state = {
            dropdownOpen: false
        };
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState(prevState => ({
            dropdownOpen: !prevState.dropdownOpen
        }));
    }

    handleClick(table){
        console.log(this.props.table)
        this.props.setTable(table);
    }

    render() {
        return (
            <div className="col-sm 10">
                <div className="row justify-content-center">
                    <div className="col-sm-10">
                        <h1>Panel administrativo</h1>
                    </div>
                </div>
                <div className="row justify-content-center">
                    <div className="col">
                        <Dropdown isOpen={this.state.dropdownOpen} toggle={this.toggle}>
                            <DropdownToggle caret>
                                Selecciona una tabla
                            </DropdownToggle>
                            <DropdownMenu>
                                {this.props.tables.map(table => {
                                    return <DropdownItem key={table.Tables_in_uvtunes} onClick={e => this.handleClick(table.Tables_in_uvtunes)}>{table.Tables_in_uvtunes}</DropdownItem>
                                })}
                            </DropdownMenu>
                        </Dropdown>
                    </div>
                </div>
                <br/>
                <div className="row justify-content-center">
                    <div className="col table-container">
                        { this.props.table !== null ? <Table table = {this.props.table} /> : null }
                    </div>
                </div>
            </div>
        );
    }
}

export default Panel;
