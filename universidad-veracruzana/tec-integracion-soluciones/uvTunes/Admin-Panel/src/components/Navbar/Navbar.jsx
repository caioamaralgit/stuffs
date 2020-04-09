import React, { Component } from 'react';
import NavbarLoggedin from './NavbarLoggedin/NavbarLoggedin';
import './Navbar.css';

class Navbar extends Component {

    handleClick(e){
        e.preventDefault();
    }

    render() {
        return (
            <nav className="navbar navbar-expand-lg justify-content-center">
                <a className="navbar-brand" href="." onClick={e => this.handleClick(e, 'index')}>uvTunes</a>
                { this.props.user !== null ? <NavbarLoggedin /> : null }
            </nav>
        );
    }
}

export default Navbar;
