import React, { Component } from 'react';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';
import faUser from '@fortawesome/fontawesome-free-solid/faUser';

class UserProfile extends Component {

  handleClick(e, view){
    e.preventDefault();
    this.props.changeView(view);
  }

  render() {
    return (
      <div className="container">
        <h2>{this.props.user.nombre}</h2>
        <div className="container">
          <div className="row justify-content-center align-items-center">
            <div className="col">
              <FontAwesomeIcon icon={faUser} size="10x"/>
            </div>
            <div className="col">
              <div className="info-box">
                <p>Información general del usuario</p>
              </div>
            </div>
          </div>
          <br/>
          <br/>
          <div className="row justify-content-between">
            <div className="col-sm-6"><button type="button" className="btn btn-primary">Subir foto</button></div>
            <div className="col-sm-4"><button type="button" className="btn btn-primary" onClick={e => this.handleClick(e, 10)}>Historial de compras</button>&nbsp;<button type="button" className="btn btn-primary">Editar información</button></div>
          </div>
          <br/>
          <div className="row justify-content-end">
            <div className="col-sm-1">
              <button type="button" className="btn btn-outline-danger">Salir</button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default UserProfile;
