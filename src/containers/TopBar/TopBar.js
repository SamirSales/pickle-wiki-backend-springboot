import React, { PureComponent } from 'react';

import './TopBar.css';
// import Tab from './Tab/Tab';

import { AuthContext } from '../Layout/Layout';
import { ScreenStatus } from '../App';

class TopBar extends PureComponent {

  render() {
    let userAuthenticated = (
      <div className="topTopBar">
        <a onClick={this.props.logout}>Sair</a>
        <a href={this.props.home} className="user-link"><i className="fa fa-user"></i> {this.props.user ? this.props.user.name : ''}</a>
      </div>
    ); 

    let userNotAuthenticated = (
      <div className="topTopBar">
        <a href={this.props.home}>Entrar</a>
        <a href={this.props.home} className="user-link"><i className="fa fa-user"></i> Não autenticado</a>
      </div>
    );

    let article = <div
      className={this.props.screenStatus === ScreenStatus.SimpleTemplate ? 'tab active' : 'tab'} 
      onClick={this.props.screenStatusEvent.bind(this, ScreenStatus.SimpleTemplate)} >Artigo</div>;

    let tabAddArticle = <div
      className={this.props.screenStatus === ScreenStatus.ArticleBuilder ? 'tab active' : 'tab'} 
      onClick={this.props.screenStatusEvent.bind(this, ScreenStatus.ArticleBuilder)} >Novo Artigo</div>;

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

            {article}

            <AuthContext.Consumer>
              { auth => auth ? tabAddArticle : null}
            </AuthContext.Consumer>
          </div>

        </div>
    )
  }
}

export default TopBar;
