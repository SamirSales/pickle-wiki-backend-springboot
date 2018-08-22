import React, { PureComponent } from 'react';
import { NavLink, withRouter } from 'react-router-dom';

import './TopBar.css';
import { AuthContext } from '../Layout/Layout';
import AutoComplete from '../../components/AutoComplete/AutoComplete';
import * as util from '../../utils';

class TopBar extends PureComponent {

  isPath(path){
    return this.props.location.pathname.substring(0, path.length) === path;
  }

  isRootPath(){
    return this.props.location.pathname === '/';
  }

  isReadingScreen(){
    return !this.isPath("/new-article") && !this.isPath("/user-editor") 
      && !this.isPath("/picture-manager") && !this.isPath("/settings");
  }

  render() {
  
    let userAuthenticated = (
      <div className="topTopBar">
        <a onClick={this.props.logout}>Sair</a>
        
        <NavLink className="user-link" to='/settings' ><i className="fa fa-cog"></i> Configurações</NavLink>

        <a href={this.props.home} className="user-link">
          <i className="fa fa-user"></i> {this.props.user ? this.props.user.name : ''}</a>
      </div>
    ); 

    let userNotAuthenticated = (
      <div className="topTopBar">
        <a onClick={this.props.login}>Entrar</a>
        <a className="user-link"><i className="fa fa-user"></i> Não autenticado</a>
      </div>
    );

    let tabRead = <NavLink className='tab' to='/article' >Leitura</NavLink>;
    let tabAddArticle = <NavLink className='tab' to='/new-article' >Novo Artigo</NavLink>;
    let tabAddNewUser = null;
    let tabPictureManager = null;
    let divSearch = null;

    if(this.props.user){
      if(util.userHasPermission(this.props.user, 'ADMIN')){
        tabAddNewUser = <NavLink className='tab' to='/user-editor' >Editores</NavLink>;
      }
      
      tabPictureManager = <NavLink className='tab' to='/picture-manager' >Imagens</NavLink>;
    }

    if(this.isReadingScreen()){
      divSearch = <AutoComplete />;
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
              { auth => auth ? tabPictureManager : null}
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
