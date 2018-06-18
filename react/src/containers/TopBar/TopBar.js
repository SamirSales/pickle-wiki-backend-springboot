import React, { PureComponent } from 'react';
import { Link, withRouter } from 'react-router-dom';
import './TopBar.css';
import { AuthContext } from '../Layout/Layout';

class TopBar extends PureComponent {

  isPath(path){
    return this.props.location.pathname.substring(0, path.length) === path;
  }

  isRootPath(){
    return this.props.location.pathname === '/';
  }

  isReadingScreen(){
    return this.isPath("/article") || this.isRootPath();
  }

  render() {

    let userAuthenticated = (
      <div className="topTopBar">
        <a onClick={this.props.logout}>Sair</a>
        <a href={this.props.home} className="user-link"><i className="fa fa-user"></i> {this.props.user ? this.props.user.name : ''}</a>
      </div>
    ); 

    let userNotAuthenticated = (
      <div className="topTopBar">
        <a onClick={this.props.login}>Entrar</a>
        <a className="user-link"><i className="fa fa-user"></i> Não autenticado</a>
      </div>
    );

    let tabRead = <Link
      className={this.isReadingScreen() ? 'tab active' : 'tab'} 
      to='/article' >Leitura</Link>;

    let tabAddArticle = <Link
      className={this.isPath("/new-article") ? 'tab active' : 'tab'} 
      to='/new-article' >Novo Artigo</Link>;

    let tabAddNewUser = <Link
      className={this.isPath("/user-editor") ? 'tab active' : 'tab'} 
      to='/user-editor' >Editores</Link>;

    let divSearch = null;

    if(this.isReadingScreen()){
      divSearch = <div className="divSearch">
          <input type="text" placeholder="Pesquisar..." />
          <i className="fa fa-search search-icon"></i>
        </div>;
    }

    return (
        <div className="topBar">
          <AuthContext.Consumer>
            { auth => auth ? userAuthenticated : null}
          </AuthContext.Consumer>

          <AuthContext.Consumer>
            { auth => !auth ? userNotAuthenticated : null}
          </AuthContext.Consumer>

          <div className="bottomTopBar">
            {divSearch}

            <AuthContext.Consumer>
              { auth => auth ? tabRead : null}
            </AuthContext.Consumer>

            <AuthContext.Consumer>
              { auth => auth ? tabAddArticle : null}
            </AuthContext.Consumer>

            <AuthContext.Consumer>
              { auth => auth ? tabAddNewUser : null}
            </AuthContext.Consumer>
          </div>

        </div>
    )
  }
}

export default withRouter(TopBar);
