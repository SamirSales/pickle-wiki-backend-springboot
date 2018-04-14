import React, { Component } from 'react';

import NavBar from '../components/NavBar/NavBar';

import logo from '../logo.svg';
import './App.css';

class App extends Component {

  state = {
    navItems: [
      { title: 'Home'},
      { title: 'About'},
      { title: 'Contact'},
      { title: 'Github'}
    ]
  }

  render() {
    return (
      <div className="App">
        <NavBar items={this.state.navItems} />
      </div>
    );
  }
}

export default App;
