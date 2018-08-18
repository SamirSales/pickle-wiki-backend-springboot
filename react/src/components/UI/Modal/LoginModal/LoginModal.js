import React, { PureComponent } from 'react';
import Modal from '../Modal';

import logo from '../../../../assets/img/lock.png';
import './LoginModal.css';

class LoginModal extends PureComponent {

    state = {
        login: '',
        password: ''
    }

    componentDidUpdate(prevProps, prevState){

        if(!prevProps.active && this.props.active){
            document.getElementById("inputLoginModal").focus();
        }

        if(!this.props.active){
            this.setState({
                login: '',
                password: ''
            });
        }
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

    onKeyPress = (event) =>{
        if (event.key === 'Enter') {
            this.props.confirm(this.state.login, this.state.password);
        }
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
                        onChange={this.onChangeLogin} onKeyPress={this.onKeyPress}/>
                    
                    <input id='inputPasswordModal' type='password' className="form-input" 
                        placeholder='Insira a senha' 
                        onChange={this.onChangePassword} onKeyPress={this.onKeyPress}/>

                    <button className="login-modal-enter-button" 
                        onClick={this.props.confirm.bind(this, this.state.login, this.state.password)}>ENTRAR</button>
                </div>
            </Modal>        
        );
    }
    
}
export default LoginModal;
