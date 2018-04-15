import React, { Component } from 'react';

import NavBar from '../components/NavBar/NavBar';
import TopBar from '../components/TopBar/TopBar';

import './App.css';

class App extends Component {

  state = {
    home: 'http://localhost:3000/',
    navItems: [
      { title: 'Home', key: '1'},
      { title: 'About', key: '2'},
      { title: 'Contact', key: '3'},
      { title: 'Github', key: '4'}
    ]
  }

  render() {
    return (
      <div className="App">
        <NavBar items={this.state.navItems} home={this.state.home} />
        <TopBar home={this.state.home} />
      </div>
    );
  }
}

export default App;
