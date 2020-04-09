import React, { Component } from 'react';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';
import faUser from '@fortawesome/fontawesome-free-solid/faUser';
import faShoppingCart from '@fortawesome/fontawesome-free-solid/faShoppingCart';

class NavBar extends Component {
  constructor(props){
    super(props);
    this.handleNavItemClick = this.handleNavItemClick.bind(this);
    this.handleClick = this.handleClick.bind(this);
  }

  handleNavItemClick(e, view){
    this.props.changeView(view);
    e.preventDefault();
  }

  handleClick(e){
    e.preventDefault();
    this.props.user !== null ? this.handleNavItemClick(e, 9) : this.handleNavItemClick(e, 3);
  }

  render() {
    return (
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <a className="navbar-brand" href="." onClick={e => this.handleNavItemClick(e, 0)}>uvTunes</a>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <form className="form-inline my-2 my-lg-0">
          <input className="form-control mr-sm-2" type="search" placeholder="Buscar" aria-label="Buscar" />
          <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
        </form>

        <div className="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">

          <ul className="navbar-nav">
            <li className={this.props.currentView === 1 ? "nav-item active" : "nav-item"}>
              <a className="nav-link" href="." onClick={e => this.handleNavItemClick(e, 1)}>Música</a>
            </li>
            <li className={this.props.currentView === 2 ? "nav-item active" : "nav-item"}>
              <a className="nav-link" href="." onClick={e => this.handleNavItemClick(e, 2)}>Vídeos</a>
            </li>
          </ul>

          <ul className="navbar-nav">
            <li className="nav-item active">
              {this.props.user === null ? null : <a className="nav-link" href="." onClick={e => this.handleNavItemClick(e, 6)}><FontAwesomeIcon icon={faShoppingCart} /></a>}
            </li>
            <li className="nav-item active">
              <a className="nav-link" href="." onClick={e => this.handleClick(e, 3)}><FontAwesomeIcon icon={faUser} /></a>
            </li>
          </ul>

        </div>
      </nav>
    );
  }
}

export default NavBar;
