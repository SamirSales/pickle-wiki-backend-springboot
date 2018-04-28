
import React from 'react';

import './TopBar.css';
import Tab from '../Tab/Tab';
import { AuthContext } from '../../containers/App';

const topBar = ( props ) => {

    let userAuthenticated = (
      <div className="topTopBar">
        <a onClick={props.logout}>Sair</a>
        <a href={props.home} className="user-link"><i className="fa fa-user"></i> {props.user ? props.user.name : ''}</a>
      </div>
    ); 

    let userNotAuthenticated = (
      <div className="topTopBar">
        <a href={props.home}>Entrar</a>
        <a href={props.home} className="user-link"><i className="fa fa-user"></i> Não autenticado</a>
      </div>
    );

    let tabAddArticle = <Tab title="Novo Artigo" />;

    return (
        <div className="topBar">
          <AuthContext.Consumer>
            { auth => auth ? userAuthenticated : null}
          </AuthContext.Consumer>

          <AuthContext.Consumer>
            { auth => !auth ? userNotAuthenticated : null}
          </AuthContext.Consumer>

          <div className="bottomTopBar">
            <div className="divSearch">
              <input type="text" placeholder="Pesquisar..." />
              <i className="fa fa-search search-icon"></i>
            </div>

            <Tab title="Artigo" active="true"/>

            <AuthContext.Consumer>
              { auth => auth ? tabAddArticle : null}
            </AuthContext.Consumer>
          </div>

        </div>
    )
};

export default topBar;
