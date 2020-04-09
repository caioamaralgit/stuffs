import React, { Component } from 'react';

class Billing extends Component {

  handleSubmit(e){
    e.preventDefault();
  }

  render() {
    return (
      <div className="container">
        <div className="row justify-content-center align-items-center">
          <div className="col-sm-6">
            <h2>Crear cuenta</h2>
            <form onSubmit={e => this.handleSubmit(e)}>
              <div class="form-group">
                <label htmlFor="exampleNombre">Nombre(s)</label>
                <input type="text" className="form-control" id="exampleNombre" aria-describedby="nombreHelp" placeholder="Nombre(s)" />
              </div>
              <div class="form-group">
                <label htmlFor="exampleApellido">Apellidos</label>
                <input type="text" className="form-control" id="exampleApellido" aria-describedby="apellidoHelp" placeholder="Apellidos" />
              </div>
              <div class="form-group">
                <label htmlFor="exampleInputEmail1">Email</label>
                <input type="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" />
              </div>
              <div className="form-group">
                <label htmlFor="rfc">RFC</label>
                <input type="text" className="form-control" id="rfc" placeholder="RFC" />
              </div>
              <div className="form-group">
                <label htmlFor="folio">Folio</label>
                <input type="text" className="form-control" id="folio" placeholder="Folio" />
              </div>
              <button type="submit" className="btn btn-primary">Aceptar</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Billing;
