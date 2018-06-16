import React, { Component } from 'react';
// import axios from 'axios';
import { getUsers, postUser, putUser, deleteUser } from "../../service/HttpHandler/HttpHandler";

import Aux from '../../hoc/Aux';
import UserItem from '../../components/UserItem/UserItem';
import EditUserModal from '../../components/UI/Modal/EditUserModal/EditUserModal';
import ConfirmModal from './ConfirmModal/ConfirmModal';
import ErrorModal from '../../components/UI/Modal/ErrorModal/ErrorModal';
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
      },
      errorModal: {
        title: 'Error',
        message: 'Não foi possível se conectar ao servidor.',
        active: false
      }
    }

    componentDidMount(){
      // eslint-disable-next-line
      const users = getUsers().then(response => {
        // console.log(response);
        this.setState({users: response.data});
      }).catch(error => {

        this.errorModal('Não foi possível carregar usuários.');
        
      });

    }

    errorModal = (msg) =>{
      this.setState({
        errorModal: {
          title: 'Error',
          message: msg,
          active: true
        }
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

    closeErrorModal = () => {
      this.setState({
        errorModal:{
          active: false
        }
      })
    }

    saveNewUser = (name, login, email, gender, password, passwordConfirm, userType) => {

      if(name.trim() === '' || login.trim() === '' || email.trim() === '' || 
        password.trim() === ''){
        this.showSnackBar('Preencha todos os campos.');
      
      }else if(password !== passwordConfirm){
        this.showSnackBar('A confirmação de senha falhou.');

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
            }).catch(error => {
              this.errorModal('Falha ao carregar usuários.');              
            });
          }).catch(error => {
            this.errorModal('Falha ao inserir usuário.'); 
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
            }).catch(error => {
              this.errorModal('Falha ao carregar usuários.');              
            });
          }).catch(error => {
            this.errorModal('Falha ao atualizar usuário.');              
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
        }).catch(error => {
          this.errorModal('Falha ao carregar usuários.');              
        });
        this.showSnackBar('Usuário removido com sucesso!');
        this.closeConfirmModal();
      }).catch(error => {
        this.errorModal('Falha ao remover o usuário.');              
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

          <ErrorModal
            title={this.state.errorModal.title}
            message={this.state.errorModal.message}
            active={this.state.errorModal.active}
            cancel={this.closeErrorModal} />

          <EditUserModal 
            user={this.state.userModal.userToEdit}
            title={this.state.userModal.title}
            active={this.state.userModal.active} 
            onSaveClick={this.saveNewUser}
            marginLeft='calc(50% - 400px)'
            cancel={this.closeUserModal}/>

          <h1 className='simple-template-title'><i className="fa fa-users"></i> Editores</h1>
          <div className='article-builder-div-content user-editor-div-user-list' style={{marginTop: '10px'}}>
            {users}
            {this.state.users.length === 0 ? emptyFieldMessage : null}
          </div>

          <button className='article-btn article-btn-topic' 
            onClick={this.openUserModal.bind(this, 'Novo Editor')} 
            style={{marginTop: '10px'}}>Novo Editor</button>

  
            
        </Aux>
      );
    }
}

export default UserEditor;