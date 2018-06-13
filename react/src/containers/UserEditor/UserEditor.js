import React, { Component } from 'react';
// import axios from 'axios';
import { getUsers, postUser, putUser, deleteUser } from "../../service/HttpHandler/HttpHandler";

import Aux from '../../hoc/Aux';
import UserItem from '../../components/UserItem/UserItem';
import EditUserModal from '../../components/UI/Modal/EditUserModal/EditUserModal';
import ConfirmModal from './ConfirmModal/ConfirmModal';
import './UserEditor.css';
 
class UserEditor extends Component {

    state = {
      users: [],
      userModal: {
        active: false,
        title: '',
        userToEdit: null
      },
      confirmModal: {
        title: '',
        question: '',
        active: false,
        userToDelete: null
      }
    }

    componentDidMount(){
      // eslint-disable-next-line
      const users = getUsers().then(response => {
        // console.log(response);
        this.setState({users: response.data});
      });

    }

    showSnackBar(text) {
      // Get the snackbar DIV
      var x = document.getElementById("snackbar");
      
      x.innerHTML = text;
  
      // Add the "show" class to DIV
      x.className = "show";
  
      // After 3 seconds, remove the show class from DIV
      setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
  }

    openUserModal = (title) => {
      this.setState({
        userModal:{
          active: true,
          title: title
        }
      });
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
              this.showSnackBar('Usuário inserido com sucesso!');
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

              this.showSnackBar('Usuário atualizado com sucesso!');
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
    }

    removeUser = () => {  
      deleteUser(this.state.confirmModal.userToDelete.id).then(res =>{  
        getUsers().then(response => {
          this.setState({users: response.data});          
        });
        this.showSnackBar('Usuário removido com sucesso!');
        this.closeConfirmModal();
      });
    }

    modalConfirmRemove = (user) => {
      const art = user.gender === 'MALE' ? 'o' : 'a';

      this.setState({
        confirmModal:{
          title: 'Remover usuário',
          question: 'Tem certeza que deseja remover '+art+' '+user.name+'?',
          active: true,
          userToDelete: user
        }
      });
    }

    closeConfirmModal = () =>{
      this.setState({
        confirmModal:{
          active: false,
          userToDelete: null
        }
      });
    }

    render() {
    
      const users = this.state.users.map(user => {
        return <UserItem user={user} key={user.id}
          removeAction={this.modalConfirmRemove.bind(this, user)}
          editAction={this.editUser.bind(this, user)} />;
      });

      const emptyFieldMessage = <p className='empty-content-message'>Nenhum editor foi adicionado.</p>;

      return (
        <Aux>

          <ConfirmModal 
            title={this.state.confirmModal.title}
            question={this.state.confirmModal.question}
            active={this.state.confirmModal.active}
            confirm={this.removeUser} 
            cancel={this.closeConfirmModal} />

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