import React, { Component } from 'react';

import Aux from '../../hoc/Aux';
import './UserEditor.css';
 
class UserEditor extends Component {

    render() {
    
        return (
          <Aux>
              <h1 className='simple-template-title'><i className="fa fa-users"></i> Editores</h1>
          </Aux>
        );
      }
}

export default UserEditor;