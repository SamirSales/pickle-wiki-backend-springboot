
import React, { Component } from 'react';

import './NavBar.css';

import logo from '../../assets/img/earth_grey.png';

class NavBar extends Component {

  NavBar(props) {
    
  }

  render() {

    let items = this.props.items.map((item) => {
      return <a align="left" 
        href={this.props.home} 
        key={item.key} onClick={this.props.click.bind(this, item.title, item.text)}>{item.title}</a>;
    });

    return (
      <div className="navBar">
        <img src={logo} alt="Earth" height="106" width="106" />
        <h5 className="title">{this.props.title}</h5>
        <h4 className="quote"><i>Sua enciclopédia pessoal</i></h4>
        {items}
      </div>
    )
  }

}

export default NavBar;
