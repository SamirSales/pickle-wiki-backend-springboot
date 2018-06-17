import React, { PureComponent } from 'react';
import { BrowserRouter } from 'react-router-dom';

import Layout from './Layout/Layout'
import SimpleTemplate from '../components/SimpleTemplate/SimpleTemplate';
import ArticleBuilder from './ArticleBuilder/ArticleBuilder';
import UserEditor from './UserEditor/UserEditor';

export const ScreenStatus = {
  SimpleTemplate: 0,
  ArticleBuilder: 1,
  UsersEdition: 2
}

class App extends PureComponent {

  state = {
    url: 'http://localhost:3000/',
    appName: 'Pickle Wiki',
    simpleTemplate:{
        title: 'Bem vindo ao Pickle Wiki',
        text: 'Sistema Wiki que tem como foco seu funcionamento em intranets empresarias e blá blá blá...'
    },
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

    let screen = null;

    switch (this.state.screenStatus){
      case ScreenStatus.SimpleTemplate:
        screen = <SimpleTemplate 
          title={this.state.simpleTemplate.title} 
          text={this.state.simpleTemplate.text} />;
        break;

      case ScreenStatus.ArticleBuilder:
        screen = <ArticleBuilder />;
        break;

      case ScreenStatus.UsersEdition:
        screen = <UserEditor />;
        break;

      default:
        console.log("unknown screen");
    }

    return (
      <BrowserRouter>
        <Layout
          url={this.state.url} 
          appName={this.state.appName}
          navItemClick={this.setSimpleTemplate}
          screenStatusEvent={this.setScreenStatus}
          screenStatus={this.state.screenStatus} >

          {screen}
        </Layout>
      </BrowserRouter>
    );
  }
}

export default App;

  
