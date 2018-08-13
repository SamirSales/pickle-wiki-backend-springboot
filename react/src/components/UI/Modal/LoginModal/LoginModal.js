import React, { PureComponent } from 'react';
import Modal from '../Modal';

import logo from '../../../../assets/img/lock.png';
import './LoginModal.css';

class LoginModal extends PureComponent {

    state = {
        login: '',
        password: ''
    }

    onChangeLogin = (event) =>{
        this.setState({
            login: event.target.value
        });
    }
    
    onChangePassword = (event) =>{
        this.setState({
            password: event.target.value
        });
    }

    render(){
        return (
            <Modal active={this.props.active} 
                cancel={this.props.cancel} 
                marginTop={this.props.marginTop}
                marginLeft={this.props.marginLeft}>              

                <div className="div-login-modal">
                    <img src={logo} height="60" width="60" alt="Lock icon" />
                    
                    <input id='inputLoginModal' className="form-input" 
                        placeholder='Insira o login' 
                        onChange={this.onChangeLogin} />
                    
                    <input id='inputPasswordModal' type='password' className="form-input" 
                        placeholder='Insira a senha' 
                        onChange={this.onChangePassword} />

                    <button className="login-modal-enter-button" 
                        onClick={this.props.confirm.bind(this, this.state.login, this.state.password)}>ENTRAR</button>
                </div>
                
                
            </Modal>        
        );
    }
    
}
export default LoginModal;
