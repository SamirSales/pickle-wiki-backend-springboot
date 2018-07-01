import React, { PureComponent } from 'react';
import { NavLink, withRouter } from 'react-router-dom';
import { connect } from 'react-redux';

import './TopBar.css';
import { AuthContext } from '../Layout/Layout';
import AutoComplete from '../../components/AutoComplete/AutoComplete';

class TopBar extends PureComponent {

  // shouldComponentUpdate(nextProps, nextState){
  //   console.log('nextProps', nextProps);
  //   console.log('nextState', nextState);

  //   return true;
  // }

  constructor(props) {
    super(props);
    // this.update = this.update.bind(this)
    // console.log(props);
  }

  isPath(path){
    return this.props.location.pathname.substring(0, path.length) === path;
  }

  isRootPath(){
    return this.props.location.pathname === '/';
  }

  isReadingScreen(){
    return this.isPath("/article") || this.isPath("/welcome");
  }

  render() {
    console.log('this.props', this.props);

    let userAuthenticated = (
      <div className="topTopBar">
        <a onClick={this.props.logout}>Sair</a>
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
    let tabAddNewUser = <NavLink className='tab' to='/user-editor' >Editores</NavLink>;
    let divSearch = null;

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
              { auth => auth ? tabAddNewUser : null}
            </AuthContext.Consumer>
          </div>

        </div>
    )
  }
}

const mapStateToProps = state => {
  return{
      usr: state.user,
  };
}

// export default withRouter(TopBar);
export default withRouter(connect(mapStateToProps)(TopBar))
