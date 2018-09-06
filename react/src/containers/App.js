import React, { PureComponent } from 'react';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';

import Layout from './Layout/Layout';
import ArticleBuilder from './ArticleBuilder/ArticleBuilder';
import UserManager from './UserManager/UserManager';
import Welcome from '../components/Welcome/Welcome';
import MarkDownHelp from '../components/MarkDownHelp/MarkDownHelp';
import FullPost from '../components/FullPost/FullPost';
import NotFoundPage from '../components/NotFoundPage/NotFoundPage';
import PictureManager from '../containers/PictureManager/PictureManager';
import Settings from '../containers/Settings/Settings';

import { connect } from 'react-redux';
import * as actionTypes from '../store/actions/actionTypes';
import * as util from '../utils';
import * as config from '../config';

class App extends PureComponent {

  render() {
    // console.log("APP", this.props.usr);

    let editArticlePage = <Route path={config.URL_HOME_PAGE+'/edit-article'} 
      component={ArticleBuilder} />;
    let newArticlePage = <Route path={config.URL_HOME_PAGE+'/new-article'} exact 
      component={ArticleBuilder} />;
    let editUsers = <Route path={config.URL_HOME_PAGE+'/user-editor'} exact 
      component={UserManager} />;
    let pictureManager = <Route path={config.URL_HOME_PAGE+'/picture-manager'} exact 
      component={PictureManager} />;
    let settings = <Route path={config.URL_HOME_PAGE+'/settings'} exact 
      component={Settings} />;

    if(this.props.usr == null){
      editArticlePage = <Redirect exact from={config.URL_HOME_PAGE+'/edit-article'} 
        to={config.URL_HOME_PAGE+'/welcome'} />;
      newArticlePage = <Redirect exact from={config.URL_HOME_PAGE+'/new-article'} 
        to={config.URL_HOME_PAGE+'/welcome'} />;
      editUsers = <Redirect exact from={config.URL_HOME_PAGE+'/user-editor'} 
        to={config.URL_HOME_PAGE+'/welcome'}/>;
      pictureManager = <Redirect exact from={config.URL_HOME_PAGE+'/picture-manager'} 
        to={config.URL_HOME_PAGE+'/welcome'}/>;
      settings = <Redirect exact from={config.URL_HOME_PAGE+'/settings'} 
        to={config.URL_HOME_PAGE+'/welcome'}/>;

    }else if(!util.userHasPermission(this.props.usr, 'ADMIN')){
      editUsers = <Redirect exact from='/user-editor' to='/welcome'/>;
    }

    return (
      <BrowserRouter>
        <Layout>
          <Switch>
            <Redirect exact from="/" to={config.URL_HOME_PAGE+'/welcome'} />
            <Redirect exact from={config.URL_HOME_PAGE+'/'} to={config.URL_HOME_PAGE+'/welcome'} />

            <Route path={config.URL_HOME_PAGE+'/'} exact component={Welcome} />
            <Route path={config.URL_HOME_PAGE+'/welcome'} exact component={Welcome} />
            <Route path={config.URL_HOME_PAGE+'/mark-down-help'} exact component={MarkDownHelp} />
            <Route path={config.URL_HOME_PAGE+'/article'} exact component={Welcome} />
            <Route path={config.URL_HOME_PAGE+'/article/:tag'} exact component={FullPost} />
            
            {editArticlePage}
            {newArticlePage}
            {editUsers}
            {pictureManager}
            {settings}

            <Route component={NotFoundPage} />
          </Switch>        
        </Layout>
      </BrowserRouter>
    );
  }
}

const mapStateToProps = state => {
  return{
      usr: state.usr.user,
      appName: state.app.appName
  };
}

const mapDispathToProps = dispatch => {
  return{
      onLogin: (usr) => dispatch({type: actionTypes.USER_LOGIN, user: usr}),
      getAppName: () => dispatch({type: actionTypes.APP_NAME})
  };
}

export default connect(mapStateToProps, mapDispathToProps)(App);

  
