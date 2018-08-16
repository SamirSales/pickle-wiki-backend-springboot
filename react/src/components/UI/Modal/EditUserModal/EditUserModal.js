import React, { PureComponent } from 'react';

import Modal from '../Modal';
import './EditUserModal.css';

class EditUserModal extends PureComponent {
    
    adminPermission = {
        id: 1, permissionType: 'ADMIN'
    };

    editorPermission = {
        id: 2, permissionType: 'EDITOR'
    };

    state = {
        name: '',
        login: '',
        password: '',
        email: '',
        gender: 'MALE',
        permissions: [this.editorPermission],
        user: null,
        editing: false
    }

    onChangeName = (event) => {
        this.setState( {
            name : event.target.value
        });
    }

    onChangeLogin = (event) => {
        this.setState( {
            login : event.target.value
        });
    }

    onChangePassword = (event) => {
        this.setState( {
            password : event.target.value
        });
    }

    onChangeEmail = (event) => {
        this.setState( {
            email : event.target.value
        });
    }

    onChangeGender = (event) => {
        this.setState( {
            gender : event.target.value
        });
    }

    onChangeUserPermissions = (event) => {
        this.setState( {
            permissions : event.target.value === 'ADMIN' ? 
                [this.editorPermission, this.adminPermission] : [this.editorPermission]
        });
    }

    setElements(name, login, email, password, gender, permissions){

        if(!password){
            password = "";
        }

        document.getElementById("edit-user-modal-name").value = name;
        document.getElementById("edit-user-modal-login").value = login;
        document.getElementById("edit-user-modal-email").value = email;
        document.getElementById("edit-user-modal-password").value = password;
        document.getElementById("edit-user-modal-select-gender").value = gender;

        if(permissions.indexOf("ADMIN") !== -1){
            document.getElementById("edit-user-modal-select-usertype").value = 'ADMIN'; 
        }else{
            document.getElementById("edit-user-modal-select-usertype").value = 'EDITOR'; 
        }   
    }

    componentDidUpdate(){

        if(!this.props.active){

            if(this.state.editing || this.state.user != null){
                this.setState({
                    editing: false,
                    user: null,
                    name: "",
                    login: "",
                    email: "",
                    password: "",
                    gender: "MALE",
                    permissions: [this.editorPermission]
                });

                this.setElements('','','','','MALE', ['EDITOR']);   
            }

        } else {
            
            if(this.props.user != null && !this.state.editing){

                this.setState({
                    editing: true,
                    user: this.props.user,
                    name: this.props.user.name,
                    login: this.props.user.login,
                    email: this.props.user.email,
                    password: this.props.user.password,
                    gender: this.props.user.gender,
                    permissions: this.props.user.permissions
                });

                const u = this.props.user;
                this.setElements(u.name,u.login,u.email,u.password,u.gender, u.permissions);
            }
        }
    }

    render(){

        return (
            <Modal active={this.props.active} 
                cancel={this.props.cancel}
                marginTop={this.props.marginTop}
                marginLeft={this.props.marginLeft} >
                
                <div className="container editUserModal">
                    <h3>{this.props.title}</h3>
                    <p className="edit-user-modal-label">Nome</p>
                    <input id="edit-user-modal-name" type="text" placeholder="Insira o nome" onChange={this.onChangeName}/>
                    
                    <p className="edit-user-modal-label">Gênero</p>
                    <select id="edit-user-modal-select-gender" onChange={this.onChangeGender}>
                        <option value="MALE">Masculino</option>
                        <option value="FEMALE">Feminino</option>
                    </select>
                    
                    <p className="edit-user-modal-label">E-mail</p>
                    <input id="edit-user-modal-email" type="text" placeholder="Insira o e-mail" onChange={this.onChangeEmail} />

                    <p className="edit-user-modal-label">Tipo de permissão</p>
                    <select id="edit-user-modal-select-usertype" onChange={this.onChangeUserPermissions}>
                        <option value="EDITOR">Editor</option>
                        <option value="ADMIN">Administrador</option>
                    </select>

                    <p className="edit-user-modal-label">Login</p>
                    <input id="edit-user-modal-login" type="text" placeholder="Insira o login" 
                        onChange={this.onChangeLogin} />

                    <p className="edit-user-modal-label">Senha</p>
                    <input id="edit-user-modal-password" type="text" placeholder="Insira a senha" 
                        onChange={this.onChangePassword} />

                    <button onClick={this.props.onSaveClick.bind(this, 
                        this.state.name, this.state.login, this.state.email,
                        this.state.gender, this.state.password, 
                        this.state.permissions)} >Salvar</button>
                </div>  
            </Modal>   
        );
    }

}

export default EditUserModal;
