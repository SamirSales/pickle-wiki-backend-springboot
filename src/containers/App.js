import React, { PureComponent } from 'react';

import NavBar from './NavBar/NavBar';
import TopBar from './TopBar/TopBar';
import SimpleTemplate from '../components/SimpleTemplate/SimpleTemplate';
import DialogConfirmation from '../components/DialogConfirmation/DialogConfirmation';

import './App.css';

export const AuthContext = React.createContext(false);

class App extends PureComponent {

  state = {
    url: 'http://localhost:3000/',
    appTitle: 'Pickle Wiki',
    user: {
      name: 'Francisco',
      login: 'chico',
      password: '123456'
    },
    simpleTemplate:{
      title: 'Bem vindo ao Pickle Wiki',
      text: 'Sistema Wiki que tem como foco seu funcionamento em intranets empresarias e blá blá blá...'
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

  dialogLogout = () => {
    this.setState( {
      dialog: {
        title: 'Fazer logout',
        message: 'Tem certeza que deseja fazer logout?',
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
      },
      user: null
    });
  }

  render() {
    return (
      <div className="App">
        <NavBar home={this.state.home}
          title={this.state.appTitle}
          click={this.setSimpleTemplate} />

        <div className="main-content">
          <AuthContext.Provider value={this.state.user !== null}>
            <TopBar home={this.state.url} 
              user={this.state.user}
              logout={this.dialogLogout} />
          </AuthContext.Provider>

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
          confirm={this.state.dialog.funConfirm} 
          cancel={this.closeDialog} />
      </div>
    );
  }
}

export default App;
