import React, { PureComponent } from 'react';
import Backdrop from '../../Backdrop/Backdrop';
import Aux from '../../../../hoc/Aux';

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
            <Aux>
                <Backdrop active={this.props.active} click={this.props.cancel} />
                
                <div className="modal-content" 
                    style={{ display: this.props.active ? 'block' : 'none' }}> 
                    <h3><i className="fa fa-lock"></i> Entrar</h3>
                    
                    <input id='inputLoginModal' className="form-input" placeholder='Login' onChange={this.onChangeLogin}/>
                    
                    <input id='inputPasswordModal' type='password' className="form-input" placeholder='Senha' 
                        onChange={this.onChangePassword} style={{marginTop: '10px'}}/>
                    
                    <div className="buttons" style={{marginTop: '10px'}}>
                        <button className="cancel" onClick={this.props.cancel}>Não</button>
                        <button onClick={this.props.confirm.bind(this, this.state.login, this.state.password)}>Sim</button>                    
                    </div>
                </div>
            </Aux>        
        );
    }
    
}
export default LoginModal;
