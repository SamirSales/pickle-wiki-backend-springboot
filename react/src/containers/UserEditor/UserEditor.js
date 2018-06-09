import React, { Component } from 'react';
// import axios from 'axios';
import { getUsers, postUser } from "../../service/HttpHandler/HttpHandler";

import Aux from '../../hoc/Aux';
import UserItem from '../../components/UserItem/UserItem';
import EditUserModal from '../../components/UI/Modal/EditUserModal/EditUserModal';
import './UserEditor.css';
 
class UserEditor extends Component {

    state = {
      users: [],
      userModal: {
        active: false,
        title: ''
      }
    }

    componentDidMount(){
      // eslint-disable-next-line
      const users = getUsers().then(response => {
        console.log(response);
        this.setState({users: response.data});
      });

    }

    openUserModal = (title) => {
      this.setState({
        userModal:{
          active: true,
          title: title
        }
      })
    }

    closeUserModal = () => {
      this.setState({
        userModal:{
          active: false
        }
      })
    }

    saveUser = () => {
      const user = {
        id: 6,
        name: 'Aline Brito',
        login: 'aline',
        password: '1122334455',
        email: 'aline@email.com',
        gender: 'FEMALE',
        userType: 'USER'
      };

      postUser(user);
    }

    render() {
    
      const users = this.state.users.map(user => {
        return <UserItem user={user} key={user.id}/>;
      });

      const emptyFieldMessage = <p className='empty-content-message'>Nenhum editor foi adicionado.</p>;

      return (
        <Aux>
            <h1 className='simple-template-title'><i className="fa fa-users"></i> Editores</h1>
            <div className='article-builder-div-content user-editor-div-user-list' style={{marginTop: '10px'}}>
              {users}
              {this.state.users.length === 0 ? emptyFieldMessage : null}
            </div>

            <button className='article-btn article-btn-topic' 
              onClick={this.openUserModal.bind(this, 'Novo Editor')} 
              style={{marginTop: '10px'}}>Novo Editor</button>

            <EditUserModal 
              title={this.state.userModal.title}
              active={this.state.userModal.active} 
              cancel={this.closeUserModal}/>
        </Aux>
      );
    }
}

export default UserEditor;