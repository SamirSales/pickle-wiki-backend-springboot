import React, { PureComponent } from 'react';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';

import Layout from './Layout/Layout';
import ArticleBuilder from './ArticleBuilder/ArticleBuilder';
import UserEditor from './UserEditor/UserEditor';
import Welcome from '../components/Welcome/Welcome';
import MarkDownHelp from '../components/MarkDownHelp/MarkDownHelp';
import FullPost from '../components/FullPost/FullPost';
import NotFoundPage from '../components/NotFoundPage/NotFoundPage';

import { connect } from 'react-redux';
import * as actionTypes from '../store/actions/actionTypes';

class App extends PureComponent {

  render() {
    let editArticlePage = <Route path='/edit-article' component={ArticleBuilder} />;
    let newArticlePage = <Route path='/new-article' exact component={ArticleBuilder} />;
    let editUsers = <Route path='/user-editor' exact component={UserEditor} />;

    if(this.props.usr == null){
      editArticlePage = <Redirect exact from='/edit-article' to='/welcome' />;
      newArticlePage = <Redirect exact from='/new-article' to='/welcome' />;
      editUsers = <Redirect exact from='/user-editor' to='/welcome'/>;

    }else if(this.props.usr.type !== 'ADMIN'){
      editUsers = <Redirect exact from='/user-editor' to='/welcome'/>;
    }

    return (
      <BrowserRouter>
        <Layout>
          <Switch>
            <Redirect exact from='/' to='/welcome' />

            <Route path='/' exact component={Welcome} />
            <Route path='/welcome' exact component={Welcome} />
            <Route path='/mark-down-help' exact component={MarkDownHelp} />
            <Route path='/article' exact component={Welcome} />
            <Route path='/article/:tag' exact component={FullPost} />
            
            {editArticlePage}
            {newArticlePage}
            {editUsers}

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

  
