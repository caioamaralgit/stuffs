import React, { Component } from 'react';
import './ProductView.css';

class Product extends Component {

  handleClick(e, view){
    e.preventDefault();
    this.props.changeView(view);
  }

  render() {
    return (
      <div className="container">
        <h2>{this.props.product.nombre}</h2>
        <div className="container">
          <div className="row justify-content-center align-items-center">
            <div className="col">
              <img className="card-img-top" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22286%22%20height%3D%22180%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20286%20180%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_162d985d8c9%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A14pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_162d985d8c9%22%3E%3Crect%20width%3D%22286%22%20height%3D%22180%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22107.1953125%22%20y%3D%2296.196875%22%3E525x330%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" alt="album"/>
            </div>
            <div className="col">
              <div className="info-box">
                <p>artista(s): {this.props.product.artistas.map(artista => {
                  return artista.nombre;
                })}</p>
                <p>descripción: {this.props.product.descripcion}</p>
                <p>año: {this.props.product.anio}</p>
                <p>precio: ${this.props.product.precio}</p>
              </div>
            </div>
          </div>
          <br/>
          <br/>
          <div className="row justify-content-end">
            <div className="col-sm-4"><button type="button" className="btn btn-primary" onClick={e => this.handleClick(e, 7)}>Comprar físico</button>&nbsp;<button type="button" className="btn btn-primary" onClick={e => this.handleClick(e, 6)}>Comprar digital</button></div>
          </div>
        </div>
      </div>
    );
  }
}

export default Product;
