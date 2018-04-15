
import React from 'react';

import './TopBar.css';

const topBar = ( props ) => {

    return (
        <div className="topBar">
          <div className="topTopBar">
            <a href="#">Entrar</a>
            <a href="#" className="user-link"><i className="fas fa-user"></i> Não autenticado</a>
          </div>

          <div className="bottomTopBar">
            <div className="divSearch">
              <input type="text" placeholder="Pesquisar..." />
              <i className="fas fa-search search-icon"></i>
            </div>
          </div>

        </div>
    )
};
export default topBar;
