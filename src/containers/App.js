import React, { Component } from 'react';

import NavBar from '../components/NavBar/NavBar';
import TopBar from '../components/TopBar/TopBar';
import SimpleTemplate from '../components/SimpleTemplate/SimpleTemplate';
import DialogConfirmation from '../components/DialogConfirmation/DialogConfirmation';

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
    },
    dialog:{
      title: '',
      message: '',
      active: false,
      funConfirm: null
    }
  }

  setSimpleTemplate = (newTitle, newText) => {  
    this.setState( {
      simpleTemplate: {
        title: newTitle,
        text: newText
      }
    });
  }

  dialogConfirmation = (newTitle, newText, confirmAction) => {

    this.setState( {
      dialog: {
        title: newTitle,
        message: newText,
        active: true, 
        funConfirm: this.logout
      }
    });
  }

  closeDialog = () => {
    this.setState( {
      dialog: {
        active: false
      }
    });
  }

  logout = () => {
    this.setState( {
      dialog: {
        active: false
      }
    });

    this.state.user = null;
  }

  render() {
    return (
      <div className="App">
        <NavBar items={this.state.navItems}
          home={this.state.home}
          title={this.state.appTitle}
          click={this.setSimpleTemplate} />

        <div className="main-content">
          <TopBar home={this.state.url} 
            user={this.state.user}
            logout={this.dialogConfirmation} />

          <div className="container">
            <SimpleTemplate 
              title={this.state.simpleTemplate.title} 
              text={this.state.simpleTemplate.text} />
          </div>
        </div>

        <DialogConfirmation 
          title={this.state.dialog.title}
          message={this.state.dialog.message}
          active={this.state.dialog.active}
          confirm={this.state.dialog.funConfirm} cancel={this.closeDialog} />
      </div>
    );
  }
}

export default App;
