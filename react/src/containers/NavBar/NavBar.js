
import React, { Component } from 'react';

import './NavBar.css';
import { Link } from 'react-router-dom';
import logo from '../../assets/img/pickle-wiki.png';

class NavBar extends Component {

  state = {
    navItems: [
      { title: 'Início', key: '1', url: '/welcome'},
      { title: 'Sobre', key: '2', url: '/welcome'},
      { title: 'Ajuda', key: '3', url: '/welcome'},
      { title: 'GitHub', key: '4', url: '/welcome'},
      { title: 'Informar erro', key: '5', url: '/welcome'},
    ]
  }

  shouldComponentUpdate(nextProps, nextState){
    // this component does not need to update
    return false;
  }

  render() {

    let items = this.state.navItems.map((item) => {
      return <Link align="left" to={item.url}
        key={item.key} onClick={this.props.itemClick.bind(this, item.title, item.text)}>{item.title}</Link>;
    });

    return (
      <div className="navBar" align="center" >
        <img src={logo} alt="Pickle Wiki" height="106" width="106" />
        <h5 className="title">{this.props.title}</h5>
        <h4 className="quote"><i>Sua enciclopédia personalisada</i></h4>
        {items}
      </div>
    )
  }

}

export default NavBar;
