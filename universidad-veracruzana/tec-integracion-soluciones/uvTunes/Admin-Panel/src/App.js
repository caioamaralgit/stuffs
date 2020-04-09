import React, { Component } from 'react';
import Login from './components/Login/Login';
import Navbar from './components/Navbar/Navbar';
import Panel from './components/Panel/Panel';
import './App.css';
import axios from "axios/index";

class App extends Component {
    constructor(){
        super();
        this.state = {
            user: {nombre: 'Alex'},
            view: 'index',
            tables: [],
            tableRows: [],
            table: null
        }
        this.setTablesNames = this.setTablesNames.bind(this);
        this.setTable = this.setTable.bind(this);
    }

    componentDidMount(){
        this.setTablesNames();
    }

    setTable(table){
        var self = this;
        axios.get('http://localhost:8000/tables/tableColumns?table=' + table)
            .then(function (response) {
                self.setState({
                    table: {
                        columns: response.data.columns,
                        data: response.data.data
                    }
                })
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    setTablesNames(){
        var self = this;
        axios.get('http://localhost:8000/tables/showTables')
            .then(function (response) {
                self.setState({
                    tables: response.data
                })
            })
            .catch(function (error) {
                console.log(error);
            });
    }

  render() {
    return (
      <div className="App">
          <Navbar user = { this.state.user } />
          { this.state.user === null && this.state.view === 'index' ? <Login user = { this.state.user } /> : null }
          { this.state.user !== null && this.state.view === 'index' ? <Panel setTablesNames = { this.setTablesNames } tables = { this.state.tables } table = {this.state.table} setTable = {this.setTable} /> : null }
      </div>
    );
  }
}

export default App;
