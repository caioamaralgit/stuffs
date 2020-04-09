import React, { Component } from 'react';

class ShoppingCartRows extends Component {
  render() {
    return (
      <tr>
        <td>{this.props.item.nombre}</td>
        <td>{this.props.item.cantidad}</td>
        <td>{this.props.item.precio}</td>
      </tr>
    );
  }
}

export default ShoppingCartRows;
