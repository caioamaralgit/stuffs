import React, { Component } from 'react';

class PurchasingHistory extends Component {

  handleClick(e, view){
    e.preventDefault();
    this.props.changeView(view);
  }

  render() {
    return (
      <div className="container">
        <div className="row justify-content-center align-items-center">
          <div className="col-sm-6">
            <h2>Historial de compras</h2>
          </div>
        </div>
        <div className="row justify-content-center align-items-center">
          <div className="col-sm-10">
            <table className="table">
              <thead>
                <tr>
                  <th scope="col">Producto</th>
                  <th scope="col">Cantidad</th>
                  <th scope="col">Precio</th>
                  <th scope="col">Fecha(aaaa/mm/dd)</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>See you on the other side</td>
                  <td>1</td>
                  <td>$100</td>
                  <td>2017/08/03</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div className="row justify-content-end">
          
        </div>
        <div className="row justify-content-end">
          <button type="submit" className="btn btn-primary" onClick={e => this.handleClick(e, 9)}>Atrás</button>
        </div>
      </div>
    );
  }
}

export default PurchasingHistory;
