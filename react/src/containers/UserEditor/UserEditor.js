import React, { Component } from 'react';
// import axios from 'axios';
import { getUsers } from "../../service/HttpHandler/HttpHandler";

import Aux from '../../hoc/Aux';
import UserItem from '../../components/UserItem/UserItem';
import './UserEditor.css';
 
class UserEditor extends Component {

    state = {
      users: []
    }

    componentDidMount(){
      // eslint-disable-next-line
      const users = getUsers().then(response => {
          // console.log(response);
          this.setState({users: response.data});
        });
    }

    render() {
    
      const users = this.state.users.map(user => {
        return <UserItem name={user.name} login={user.login} email={user.email}/>;
      });

      const emptyFieldMessage = <p className='empty-content-message'>Nenhum editor foi adicionado.</p>;

        return (
          <Aux>
              <h1 className='simple-template-title'><i className="fa fa-users"></i> Editores</h1>
              <div className='article-builder-div-content user-editor-div-user-list' style={{marginTop: '10px'}}>
                {users}
                {this.state.users.length === 0 ? emptyFieldMessage : null}
              </div>

              <button className='article-btn article-btn-topic' style={{marginTop: '10px'}}>Novo Editor</button>
          </Aux>
        );
      }
}

export default UserEditor;