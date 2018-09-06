
import React, { Component } from 'react';

import './NavBar.css';
import { Link } from 'react-router-dom';
import logo from '../../assets/img/try-icon.png';
import * as config from '../../config';

class NavBar extends Component {

  shouldComponentUpdate(nextProps, nextState){
    // this component does not need to update
    return false;
  }

  render() {

    // eslint-disable-next-line
    const gitHubLink = <a align='left' href="https://github.com/SamirSales/pickle-wiki" target="_blank">GitHub</a>;
    // eslint-disable-next-line
    const errorLink = <a align='left' href="https://github.com/SamirSales/pickle-wiki/issues" target="_blank">Informar erro</a>;
    
    return (
      <div className="navBar" align="center" >
        <img src={logo} alt="Pickle Wiki" height="106" width="106" />
        <h5 className="title">{this.props.title}</h5>
        <h4 className="quote"><i>Sua enciclopédia personalisada</i></h4>

        <Link align="left" to={config.URL_HOME_PAGE+'/welcome'} >Início</Link>
        <Link align="left" to={config.URL_HOME_PAGE+'/mark-down-help'} >Markdown</Link>
        
        {gitHubLink}
        {errorLink}
      </div>
    )
  }

}

export default NavBar;
