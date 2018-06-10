import React, { PureComponent } from 'react';

import Aux from '../../../../hoc/Aux';
import Backdrop from '../../Backdrop/Backdrop';
import './EditUserModal.css';

class EditUserModal extends PureComponent {
    
    state = {
        id: null,
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

    render(){
        return (
            <Aux>
                <Backdrop active={this.props.active} click={this.props.cancel} />
                
                <div className="editUserModal edit-user-modal-animation" 
                    style={{ display: this.props.active ? 'block' : 'none' }}>      
    
                    <div className="container">
                        <h3>{this.props.title}</h3>
                        <input type="text" placeholder="Nome" onChange={this.onChangeName}/>
                        <input type="text" placeholder="Login" onChange={this.onChangeLogin} />
                        <input type="text" placeholder="E-mail" onChange={this.onChangeEmail} />

                        <select onChange={this.onChangeGender}>
                            <option value="MALE">Masculino</option>
                            <option value="FEMALE">Feminino</option>
                        </select>

                        <select onChange={this.onChangeUserType}>
                            <option value="EDITOR">Editor</option>
                            <option value="ADMIN">Administrado</option>
                        </select>

                        <input type="password" placeholder="Senha" onChange={this.onChangePassword} />
                        <input type="password" placeholder="Confirme a senha" 
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
