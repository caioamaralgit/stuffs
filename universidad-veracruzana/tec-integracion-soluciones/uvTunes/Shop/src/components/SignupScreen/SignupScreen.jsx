import React, { Component } from 'react';
import axios from "axios/index";
import server from '../../config.json';

class SignupScreen extends Component {

  handleSubmit(e){
    e.preventDefault();
    this.checkForm();
  }

  checkForm() {
    let formElements = this.form.elements;

    for (var i = 0; i < formElements.length; i++) {
      if (formElements[i].value.trim() === "") {
        console.log("El campo \"" + formElements[i].placeholder + "\" debe ser llenado!");
        
      }
    }

    let data = { 
      "nombre" : this.nombre.value,
      "apellido" : this.apellido.value,
      "fecha_nacimiento" : this.fecha_nacimiento.value,
      "email" : this.email.value,
      "contrasena" : this.contrasena.value,
      "direccion" : this.direccion.value,
      "cp" : this.cp.value,
      "ciudad" : this.ciudad.value,
      "estado" : this.estado.value
    };

    this.register(data);
  }
  
  register(data){
    axios.post('http://' + server.address + ':' + server.port + '/shop/register/', data)
      .then(function (response) {
          if (response.data) alert("Exito en la solicitud");
          else alert("No fue posible registrarse");
        })
      .catch(function (error) {
          alert("Falla en la solicitud");
      });
  }

  render() {
    return (
      <div className="container">
        <div className="row justify-content-center align-items-center">
          <div className="col-sm-6">
            <h2>Crear cuenta</h2>
            <form onSubmit={e => this.handleSubmit(e)} ref={(form) => this.form = form}>
              <div className="form-group">
                <label htmlFor="formNombre">Nombre</label>
                <input type="text" className="form-control" id="formNombre" aria-describedby="nombreHelp" placeholder="Nombre" ref={(input) => this.nombre = input}/>
              </div>
              <div className="form-group">
                <label htmlFor="formApellido">Apellido</label>
                <input type="text" className="form-control" id="formApellido" aria-describedby="apellidoHelp" placeholder="Apellido" ref={(input) => this.apellido = input}/>
              </div>
              <div className="form-group">
                  <label htmlFor="formNacimiento">Fecha de nacimiento</label>
                  <input type="text" className="form-control" id="formNacimiento" aria-describedby="fecha_nacimiento" placeholder="Fecha de nacimiento" ref={(input) => this.fecha_nacimiento = input}/>
              </div>
              <div className="form-group">
                <label htmlFor="formEmail">Email</label>
                <input type="email" className="form-control" id="formEmail" aria-describedby="emailHelp" placeholder="Email" ref={(input) => this.email = input}/>
              </div>
              <div className="form-group">
                <label htmlFor="formContrasena">Contraseña</label>
                <input type="password" className="form-control" id="formContrasena" placeholder="Contraseña" ref={(input) => this.contrasena = input}/>
              </div>
              <div className="form-group">
                <label htmlFor="formVContrasena">Verificar contraseña</label>
                <input type="password" className="form-control" id="formVContrasena" placeholder="Contraseña" ref={(input) => this.password2 = input}/>
              </div>
              <div className="form-group">
                  <label htmlFor="formDireccion">Dirección</label>
                  <input type="text" className="form-control" id="direccion" aria-describedby="formDireccion" placeholder="Dirección" ref={(input) => this.direccion = input}/>
              </div>
              <div className="form-group">
                  <label htmlFor="formCP">Código postal</label>
                  <input type="text" className="form-control" id="formCP" aria-describedby="cp" placeholder="Código postal" ref={(input) => this.cp = input}/>
              </div>
              <div className="form-group">
                  <label htmlFor="formCiudad">Ciudad</label>
                  <input type="text" className="form-control" id="formCiudad" aria-describedby="ciudad" placeholder="Ciudad" ref={(input) => this.ciudad = input}/>
              </div>
              <div className="form-group">
                  <label htmlFor="formEstado">Estado</label>
                  <input type="text" className="form-control" id="formEstado" aria-describedby="estado" placeholder="Estado" ref={(input) => this.estado = input}/>
              </div>
              <button type="submit" className="btn btn-primary">Aceptar</button><br /><br />
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default SignupScreen;
