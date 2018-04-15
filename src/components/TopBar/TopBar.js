
import React from 'react';

import './TopBar.css';

const topBar = ( props ) => {

    return (
        <div className="topBar">
          <div className="topTopBar">
            <a href={props.home}>Entrar</a>
            <a href={props.home} className="user-link"><i className="fa fa-user"></i> Não autenticado</a>
          </div>

          <div className="bottomTopBar">
            <div className="divSearch">
              <input type="text" placeholder="Pesquisar..." />
              <i className="fa fa-search search-icon"></i>
            </div>
          </div>

        </div>
    )
};
export default topBar;
