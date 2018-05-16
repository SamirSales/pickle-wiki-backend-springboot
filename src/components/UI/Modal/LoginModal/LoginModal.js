
import React from 'react';
import Backdrop from '../../Backdrop/Backdrop';
import Aux from '../../../../hoc/Aux';

import './LoginModal.css';

const loginModal = ( props ) => {

    return (
        <Aux>
            <Backdrop active={props.active} click={props.cancel} />
            
            <div className="modal-content" 
                style={{ display: props.active ? 'block' : 'none' }}> 
                <h3><i className="fa fa-lock"></i> Entrar</h3>
                <input className="form-input" placeholder='Login' onChange={props.onchangelogin}/>
                <input className="form-input" placeholder='Senha...' onChange={props.onchangepassword} style={{marginTop: '10px'}}/>
                <div className="buttons" style={{marginTop: '10px'}}>
                    <button className="cancel" onClick={props.cancel}>Não</button>
                    <button onClick={props.confirm}>Sim</button>                    
                </div>
            </div>
        </Aux>        
    )
};
export default loginModal;
