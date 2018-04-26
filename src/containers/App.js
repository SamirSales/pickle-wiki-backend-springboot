import React, { Component } from 'react';

import NavBar from '../components/NavBar/NavBar';
import TopBar from '../components/TopBar/TopBar';
import SimpleTemplate from '../components/SimpleTemplate/SimpleTemplate';

import './App.css';

class App extends Component {

  state = {
    url: 'http://localhost:3000/',
    appTitle: 'Pickle Wiki',
    user: {
      name: 'Francisco',
      login: 'chico',
      password: '123456'
    },
    navItems: [
      { title: 'Início', key: '1', text: 'Hello início...'},
      { title: 'Sobre', key: '2', text: 'Hello sobre...'},
      { title: 'Ajuda', key: '3', text: 'Hello ajuda...'},
      { title: 'Github', key: '4', text: 'Hello github...'},
      { title: 'Informar erro', key: '5', text: 'Hello informar erro...'}
    ],
    simpleTemplate:{
      title: 'Bem vindo ao Pickle Wiki',
      text: 'Blá blá blá 2'
    }
  }

  switchNameHandler = (newTitle, newText) => {
    
    this.setState( {
      simpleTemplate: {
        title: newTitle,
        text: newText
      }
    } );
  }

  render() {
    return (
      <div className="App">
        <NavBar items={this.state.navItems}
          home={this.state.home}
          title={this.state.appTitle}
          click={this.switchNameHandler} />

        <div className="main-content">
          <TopBar home={this.state.url} user={this.state.user}/>
          <div className="container">
            <SimpleTemplate 
              title={this.state.simpleTemplate.title} 
              text={this.state.simpleTemplate.text} />
          </div>
        </div>

      </div>
    );
  }
}

export default App;
