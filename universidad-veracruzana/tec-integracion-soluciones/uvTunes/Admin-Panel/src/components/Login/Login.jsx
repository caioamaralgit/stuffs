import React, { Component } from 'react';
import './Login.css';

class Login extends Component {
    constructor(){
        super();
        this.state = {
            usuario: null,
        }
    }

    handleClick(e){
        e.preventDefault();
    }

    render() {
        return (
            <div className="container login">
                <div className="row justify-content-center">
                    <div className="col-sm-6">
                        <h2><b>Autenticación</b></h2>
                        <form onSubmit={e => this.handleClick(e)}>
                            <div className="form-group">
                                <label htmlFor="user">Usuario</label>
                                <input type="text" className="form-control" id="user"
                                       aria-describedby="user" placeholder="Usuario" />
                            </div>
                            <div className="form-group">
                                <label htmlFor="exampleInputPassword1">Contraseña</label>
                                <input type="password" className="form-control" id="exampleInputPassword1"
                                       placeholder="Contraseña" />
                            </div>
                            <button type="submit" className="btn btn-primary">Aceptar</button>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default Login;
