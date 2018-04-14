
import React from 'react';

import './NavBar.css';

import logo from '../../assets/img/earth.png';

const navBar = ( props ) => {

    let items = props.items.map((item) => {
      return <a align="left" href="#">{item.title}</a>;
    })

    return (
        <div className="navBar">
          <img src={logo} alt="Earth" height="110" width="110" />
          <h5 className="title">My Week</h5>
          {items}
        </div>
    )
};
export default navBar;
