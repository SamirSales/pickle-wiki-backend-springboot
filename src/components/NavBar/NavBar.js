
import React, { Component } from 'react';

import './NavBar.css';

import logo from '../../assets/img/pickle-wiki.png';

class NavBar extends Component {

  NavBar(props) {
    
  }

  state = {
    navItems: [
      { title: 'Início', key: '1', text: 'Hello início...'},
      { title: 'Sobre', key: '2', text: 'Hello sobre...'},
      { title: 'Ajuda', key: '3', text: 'Hello ajuda...'},
      { title: 'Github', key: '4', text: 'Hello github...'},
      { title: 'Informar erro', key: '5', text: 'Hello informar erro...'}
    ]
  }

  shouldComponentUpdate(nextProps, nextState){
    // this component does not need to update
    return false;
  }

  render() {

    let items = this.state.navItems.map((item) => {
      return <a align="left" 
        href={this.props.home} 
        key={item.key} onClick={this.props.click.bind(this, item.title, item.text)}>{item.title}</a>;
    });

    return (
      <div className="navBar">
        <img src={logo} alt="Pickle Wiki" height="106" width="106" />
        <h5 className="title">{this.props.title}</h5>
        <h4 className="quote"><i>Sua enciclopédia personalisada</i></h4>
        {items}
      </div>
    )
  }

}

export default NavBar;
