import React, { Component } from 'react';
import ShoppingCartRows from './ShoppingCartRows/ShoppingCartRows';

class ShoppingCartView extends Component {
  constructor(props){
    super(props);
    this.state = {
      total: 0
    }
  }

  componentDidMount(){
    var total = 0;
    for(var i = 0; i < this.props.items.length; i++){
      total += parseInt(this.props.items[i].precio, 10);
    }
    this.setState({
      total: total
    })
  }

  handleClick(e, view){
    e.preventDefault();
    this.props.changeView(view);
  }

  render() {
    return (
      <div className="container">
        <div className="row justify-content-center align-items-center">
          <div className="col-sm-6">
            <h2>Carrito de compras</h2>
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
                </tr>
              </thead>
              <tbody>
                {this.props.items.map(item => {
                  return <ShoppingCartRows item = {item} />;
                })}
              </tbody>
            </table>
          </div>
        </div>
        <div className="row justify-content-end">
          <h4>Total: ${this.state.total}</h4>
        </div>
        <div className="row justify-content-end">
          <button type="submit" className="btn btn-primary" onClick={e => this.handleClick(e, 8)}>Comprar</button>
        </div>
      </div>
    );
  }
}

export default ShoppingCartView;
