import React, { PureComponent } from 'react';

import Layout from './Layout/Layout'
import SimpleTemplate from '../components/SimpleTemplate/SimpleTemplate';

class App extends PureComponent {

  state = {
    url: 'http://localhost:3000/',
    appName: 'Pickle Wiki',
    simpleTemplate:{
        title: 'Bem vindo ao Pickle Wiki',
        text: 'Sistema Wiki que tem como foco seu funcionamento em intranets empresarias e blá blá blá...'
    },
  }

  setSimpleTemplate = (newTitle, newText) => {  
    this.setState( {
        simpleTemplate: {
            title: newTitle,
            text: newText
        }
    });
  }

  render() {
    return (
      <Layout
        url={this.state.url} 
        appName={this.state.appName}
        navItemClick={this.setSimpleTemplate}>

        <SimpleTemplate 
          title={this.state.simpleTemplate.title} 
          text={this.state.simpleTemplate.text} />  
      </Layout>
    );
  }
}

export default App;
