import React, { Component } from 'react';
import axios from 'axios';

import Aux from '../../hoc/Aux';
import './UserEditor.css';
 
class UserEditor extends Component {

    componentDidMount(){
      const users = axios.get('http://localhost:8080/users/')
        .then(response => {
          console.log(response);
        });
    }

    render() {
    
        return (
          <Aux>
              <h1 className='simple-template-title'><i className="fa fa-users"></i> Editores</h1>
              <div className='article-builder-div-content user-editor-div-user-list' style={{marginTop: '10px'}}>
                <p className='empty-content-message'>Nenhum editor foi adicionado.</p>
              </div>

              <button className='article-btn article-btn-topic' style={{marginTop: '10px'}}>Novo Editor</button>
          </Aux>
        );
      }
}

export default UserEditor;