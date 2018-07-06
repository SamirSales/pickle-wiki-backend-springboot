import React, { PureComponent } from 'react';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';

import Layout from './Layout/Layout';
import ArticleBuilder from './ArticleBuilder/ArticleBuilder';
import UserEditor from './UserEditor/UserEditor';
import Welcome from '../components/Welcome/Welcome';
import MarkDownHelp from '../components/MarkDownHelp/MarkDownHelp';
import FullPost from '../components/FullPost/FullPost';
import NotFoundPage from '../components/NotFoundPage/NotFoundPage';

class App extends PureComponent {

  render() {
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

  
