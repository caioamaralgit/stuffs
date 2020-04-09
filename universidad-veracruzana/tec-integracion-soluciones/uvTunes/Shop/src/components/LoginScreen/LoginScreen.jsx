import React, { Component } from 'react';
import axios from "axios/index";
import server from '../../config.json';

class LoginScreen extends Component {

  handleSubmit(e){
    e.preventDefault();
    
    let data = {
      "email" : this.email.value,
      "contrasena" : this.contrasena.value
    }

    axios.post('http://' + server.address + ':' + server.port + '/shop/login/', data)
      .then(function (response) {
          if (response.data.exito) alert("Exito en la solicitud");
          else alert(response.data.error);
        })
      .catch(function (error) {
          alert("Falla en la solicitud");
      });
  }

  render(){
    return(
      <div className="container">
        <div className="row justify-content-center align-items-center">
          <div className="col-sm-6">
            <h2>Entrar</h2>
            <form onSubmit={e => this.handleSubmit(e)}>
              <div className="form-group">
                <label htmlFor="formEmail">Email</label>
                <input type="email" className="form-control" id="formEmail" aria-describedby="emailHelp" placeholder="Email" ref={(input) => this.email = input} />
              </div>
              <div className="form-group">
                <label htmlFor="formContrasena">Contraseña</label>
                <input type="password" className="form-control" id="formContrasena" placeholder="Contraseña" ref={(input) => this.contrasena = input} />
              </div>
              <button type="submit" className="btn btn-primary">Entrar</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default LoginScreen;
