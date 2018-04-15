
import React from 'react';

import './NavBar.css';

import logo from '../../assets/img/earth_grey.png';

const navBar = ( props ) => {

    let items = props.items.map((item) => {
      return <a align="left" href={props.home} key={item.key}>{item.title}</a>;
    })

    return (
        <div className="navBar">
          <img src={logo} alt="Earth" height="106" width="106" />
          <h5 className="title">{props.title}</h5>
          <h4 className="quote"><i>Sua enciclopédia pessoal</i></h4>
          {items}
        </div>
    )
};
export default navBar;
