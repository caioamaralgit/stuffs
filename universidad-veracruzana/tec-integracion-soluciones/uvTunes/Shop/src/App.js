import React, { Component } from 'react';
import NavBar from './components/NavBar/NavBar';
import Index from './components/Index/Index';
import ItemsGrid from './components/ItemsGrid/ItemsGrid';
import LoginScreen from './components/LoginScreen/LoginScreen';
import SignupScreen from './components/SignupScreen/SignupScreen';
import ProductView from './components/ProductView/ProductView';
import ShoppingCartView from './components/ShoppingCartView/ShoppingCartView';
import SelectPhysicalItemQuantity from './components/SelectPhysicalItemQuantity/SelectPhysicalItemQuantity';
import PaymentView from './components/PaymentView/PaymentView';
import UserProfile from './components/UserProfile/UserProfile';
import PurchasingHistory from './components/PurchasingHistory/PurchasingHistory';
import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      view: 0,
      user: null,
      product: null,
      shoppingCartItems: [
        {id_producto:"asd23", nombre: "See you on the other side", artistas: [{nombre: "KoRn"}], descripcion: "Salió a la venta el 6 de diciembre de 2005 y fue grabado en el estudio de la casa del vocalista Jonathan Davis donde se grabó el disco Take a Look in the Mirror.", anio: "2005", imagen: "", precio: "100", cantidad: 3, tipo: 1},
        {id_producto: "lksdf23", nombre: "In rainbows", artistas: [{nombre: "Radiohead"}], descripcion: "Radiohead trabajó en el álbum durante más de dos años con los productores Mark Stent y Nigel Godrich, comenzando a principios de 2005. Durante el proceso, la banda salió de gira tres meses por Europa y Estados Unidos a mediados de 2006.", anio: "2007", imagen: "", precio: "200", cantidad: 1, tipo: 1},
      ],
      physicalProduct: {id_producto:"asd23", nombre: "See you on the other side", artistas: [{nombre: "KoRn"}], descripcion: "Salió a la venta el 6 de diciembre de 2005 y fue grabado en el estudio de la casa del vocalista Jonathan Davis donde se grabó el disco Take a Look in the Mirror.", anio: "2005", imagen: "", precio: "100", cantidad: 3},
      deliveryData: {price: 700, estimatedTime: "3 días"}
    };
    this.handleViewChange = this.handleViewChange.bind(this);
    this.getProduct = this.getProduct.bind(this);
    this.addShoppingCartItem = this.addShoppingCartItem.bind(this);
    this.removeShoppingCartItem = this.removeShoppingCartItem.bind(this);
  }

  getProduct(product){
    this.setState({
      product: product
    });
    this.handleViewChange(5);
  }

  addShoppingCartItem(item){
    this.setState({
      shoppingCartItems: [...this.state.shoppingCartItems, item]
    })
  }

  removeShoppingCartItem(item){
    var items = this.state.shoppingCartItems;
    for (var i = 0; i < items.length; i++) {
      if(item === items[i].id_producto){
        items[i].pop();
        return true;
      }
    }
    return false;
  }

  handleViewChange(view){
    this.setState({
      view: view
    })
  }

  render() {
    return (
      <div className="App">
        <NavBar user = {this.state.user} currentView = {this.state.view} changeView = {this.handleViewChange}/>
        { this.state.view === 0 ? <Index changeView = {this.handleViewChange} user = {this.state.user} /> : null }
        { this.state.view === 1 || this.state.view === 2 ? <ItemsGrid itemsContext = {this.state.view} changeView = {this.handleViewChange} getProduct = {this.getProduct} /> : null }
        { this.state.view === 3 ? <LoginScreen /> : null }
        { this.state.view === 4 ? <SignupScreen /> : null }
        { this.state.view === 5 ? <ProductView product = {this.state.product} addShoppingCartItem = {this.addShoppingCartItem} changeView = {this.handleViewChange} /> : null }
        { this.state.view === 6 ? <ShoppingCartView items = {this.state.shoppingCartItems} removeShoppingCartItem = {this.removeShoppingCartItem} changeView = {this.handleViewChange} /> : null }
        { this.state.view === 7 ? <SelectPhysicalItemQuantity product = {this.state.physicalProduct} changeView = {this.handleViewChange} /> : null }
        { this.state.view === 8 ? <PaymentView deliveryData = {this.state.deliveryData} /> : null }
        { this.state.view === 9 ? <UserProfile user = {this.state.user} changeView = {this.handleViewChange} /> : null }
        { this.state.view === 10 ? <PurchasingHistory changeView = {this.handleViewChange} /> : null }
      </div>
    );
  }
}

export default App;
