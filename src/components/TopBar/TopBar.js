
import React from 'react';

import './TopBar.css';
import Tab from '../Tab/Tab';

const topBar = ( props ) => {

    let itemUser =  null;
    let tabAddArticle = null;

    let dialogLogoutMsg = {
      title: 'Fazer logout',
      message: 'Tem certeza que deseja sair da aplicação?'
    };

    if(props.user == null){
      itemUser = (
        <div className="topTopBar">
          <a href={props.home}>Entrar</a>
          <a href={props.home} className="user-link"><i className="fa fa-user"></i> Não autenticado</a>
        </div>
      );
    } else {
      itemUser = (
        <div className="topTopBar">
          <a onClick={props.logout.bind(this, dialogLogoutMsg.title, dialogLogoutMsg.message, null)}>Sair</a>
          <a href={props.home} className="user-link"><i className="fa fa-user"></i> {props.user.name}</a>
        </div>
      );

      tabAddArticle = <Tab title="Novo Artigo" />;
    }

    return (
        <div className="topBar">
          {itemUser}

          <div className="bottomTopBar">
            <div className="divSearch">
              <input type="text" placeholder="Pesquisar..." />
              <i className="fa fa-search search-icon"></i>
            </div>

            <Tab title="Artigo" active="true"/>
            {tabAddArticle}
          </div>

        </div>
    )
};

export default topBar;
