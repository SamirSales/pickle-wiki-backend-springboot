import React, { Component } from 'react';
// import axios from 'axios';
import { getUsers, postUser, putUser } from "../../service/HttpHandler/HttpHandler";

import Aux from '../../hoc/Aux';
import UserItem from '../../components/UserItem/UserItem';
import EditUserModal from '../../components/UI/Modal/EditUserModal/EditUserModal';
import './UserEditor.css';
 
class UserEditor extends Component {

    state = {
      users: [],
      userModal: {
        active: false,
        title: '',
        userToEdit: null
      }
    }

    componentDidMount(){
      // eslint-disable-next-line
      const users = getUsers().then(response => {
        // console.log(response);
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

    saveNewUser = (name, login, email, gender, password, passwordConfirm, userType) => {

      if(name.trim() === '' || login.trim() === '' || email.trim() === '' || 
        password.trim() === ''){
        console.log("Preencha todos os campos.");
      
      }else if(password !== passwordConfirm){
        console.log("A confirmação de senha falhou.");

      }else{
        const user = {
          id: this.state.userModal.userToEdit != null ? this.state.userModal.userToEdit.id : null,
          name: name.trim(),
          login: login.trim(),
          email: email.trim(),
          password: password.trim(),
          gender: gender,
          userType: userType
        };

        if(this.state.userModal.userToEdit == null){
          // insert new user
          postUser(user).then(res => {
            getUsers().then(response => {
              this.setState({users: response.data});
            });
          });

        }else{
          // update user
          putUser(user).then(res => {
            getUsers().then(response => {
              this.setState({
                users: response.data,
                userModal: ({
                  userToEdit: null
                })
              });
            });
          });
        }

        this.closeUserModal();
      }
    }

    editUser = (user) => {

      this.setState({
        userModal: ({
          active: true,
          title: user.name,
          userToEdit: user
        })
      });

      console.log(user);
    }

    removeUser = (user) => {
      console.log("remove "+user);
    }

    render() {
    
      const users = this.state.users.map(user => {
        return <UserItem user={user} key={user.id}
          removeAction={this.removeUser.bind(this, user)}
          editAction={this.editUser.bind(this, user)} />;
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
              user={this.state.userModal.userToEdit}
              title={this.state.userModal.title}
              active={this.state.userModal.active} 
              onSaveClick={this.saveNewUser}
              cancel={this.closeUserModal}/>
        </Aux>
      );
    }
}

export default UserEditor;