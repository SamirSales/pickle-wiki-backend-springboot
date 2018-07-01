import React, { PureComponent } from 'react';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';

import Layout from './Layout/Layout';
import ArticleBuilder from './ArticleBuilder/ArticleBuilder';
import UserEditor from './UserEditor/UserEditor';
import Welcome from '../components/Welcome/Welcome';
import FullPost from '../components/FullPost/FullPost';
import NotFoundPage from '../components/NotFoundPage/NotFoundPage';

export const ScreenStatus = {
  SimpleTemplate: 0,
  ArticleBuilder: 1,
  UsersEdition: 2
}

class App extends PureComponent {

  state = {
    appName: 'Pickle Wiki',
    screenStatus: ScreenStatus.UsersEdition
  }

  setSimpleTemplate = (newTitle, newText) => {  
    this.setState( {
        simpleTemplate: {
            title: newTitle,
            text: newText
        },
        screenStatus: ScreenStatus.SimpleTemplate
    });
  }

  setScreenStatus = (newScreenStatus) => {
    this.setState( {
      screenStatus: newScreenStatus
    });
  }

  render() {
    return (
      <BrowserRouter>
        <Layout
          url={this.state.url} 
          appName={this.state.appName}
          navItemClick={this.setSimpleTemplate}
          screenStatusEvent={this.setScreenStatus}
          screenStatus={this.state.screenStatus} >

          <Switch>
            <Redirect exact from='/' to='/welcome' />

            <Route path='/' exact component={Welcome} />
            <Route path='/welcome' exact component={Welcome} />
            <Route path='/article' exact component={Welcome} />
            <Route path='/article/:tag' exact component={FullPost} />
            
            <Route path='/edit-article' component={ArticleBuilder} />
            <Route path='/new-article' exact component={ArticleBuilder} />

            <Route path='/user-editor' exact component={UserEditor} />

            <Route component={NotFoundPage} />
          </Switch>        
        </Layout>
      </BrowserRouter>
    );
  }
}

export default App;

  
