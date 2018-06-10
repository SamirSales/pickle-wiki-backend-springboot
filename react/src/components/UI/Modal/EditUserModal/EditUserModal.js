import React, { PureComponent } from 'react';

import Aux from '../../../../hoc/Aux';
import Backdrop from '../../Backdrop/Backdrop';
import './EditUserModal.css';

class EditUserModal extends PureComponent {
    
    state = {
        name: '',
        login: '',
        password: '',
        passwordConfirmation: '',
        email: '',
        gender: 'MALE',
        userType: 'EDITOR'
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

    onChangePasswordConfirmation = (event) => {
        this.setState( {
            passwordConfirmation : event.target.value
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

    onChangeUserType = (event) => {
        this.setState( {
            userType : event.target.value
        });
    }

    componentDidUpdate(){

        if(!this.props.active){
            document.getElementById("edit-user-modal-name").value = "";
            document.getElementById("edit-user-modal-login").value = "";
            document.getElementById("edit-user-modal-email").value = "";
            document.getElementById("edit-user-modal-password").value = "";
            document.getElementById("edit-user-modal-password-confirm").value = "";
            document.getElementById("edit-user-modal-select-gender").value = "MALE";
            document.getElementById("edit-user-modal-select-usertype").value = "EDITOR";
        }
    }

    render(){

        return (
            <Aux>
                <Backdrop active={this.props.active} click={this.props.cancel} />
                
                <div className="editUserModal edit-user-modal-animation" 
                    style={{ display: this.props.active ? 'block' : 'none' }}>      
    
                    <div className="container">
                        <h3>{this.props.title}</h3>
                        <input id="edit-user-modal-name" type="text" placeholder="Nome" onChange={this.onChangeName}/>
                        <input id="edit-user-modal-login" type="text" placeholder="Login" onChange={this.onChangeLogin} />
                        <input id="edit-user-modal-email" type="text" placeholder="E-mail" onChange={this.onChangeEmail} />

                        <select id="edit-user-modal-select-gender" onChange={this.onChangeGender}>
                            <option value="MALE">Masculino</option>
                            <option value="FEMALE">Feminino</option>
                        </select>

                        <select id="edit-user-modal-select-usertype" onChange={this.onChangeUserType}>
                            <option value="EDITOR">Editor</option>
                            <option value="ADMIN">Administrado</option>
                        </select>

                        <input id="edit-user-modal-password" type="password" placeholder="Senha" onChange={this.onChangePassword} />
                        <input id="edit-user-modal-password-confirm" type="password" placeholder="Confirme a senha" 
                            onChange={this.onChangePasswordConfirmation}/>
    
                        <button onClick={this.props.onSaveClick.bind(this, 
                            this.state.name, this.state.login, this.state.email,
                            this.state.gender, this.state.password, 
                            this.state.passwordConfirmation,
                            this.state.userType)} >Salvar</button>
                    </div>
                </div>    
            </Aux>   
        );
    }

}

export default EditUserModal;
