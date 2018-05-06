import React, { PureComponent } from 'react';

import Layout from './Layout/Layout'
import SimpleTemplate from '../components/SimpleTemplate/SimpleTemplate';
import ArticleBuilder from '../components/ArticleBuilder/ArticleBuilder';

export const ScreenStatus = {
  SimpleTemplate: 0,
  ArticleBuilder: 1
}

class App extends PureComponent {

  state = {
    url: 'http://localhost:3000/',
    appName: 'Pickle Wiki',
    simpleTemplate:{
        title: 'Bem vindo ao Pickle Wiki',
        text: 'Sistema Wiki que tem como foco seu funcionamento em intranets empresarias e blá blá blá...'
    },
    screenStatus: ScreenStatus.ArticleBuilder
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

    let simpleTemp = null;

    if(this.state.screenStatus === ScreenStatus.SimpleTemplate){
      simpleTemp = <SimpleTemplate 
        title={this.state.simpleTemplate.title} 
        text={this.state.simpleTemplate.text} />;
    }

    let artBuilder = null; 

    if(this.state.screenStatus === ScreenStatus.ArticleBuilder){
      artBuilder = <ArticleBuilder />;
    }

    return (
      <Layout
        url={this.state.url} 
        appName={this.state.appName}
        navItemClick={this.setSimpleTemplate}
        screenStatusEvent={this.setScreenStatus}
        screenStatus={this.state.screenStatus} >

        {simpleTemp}

        {artBuilder}
      </Layout>
    );
  }
}

export default App;

  
