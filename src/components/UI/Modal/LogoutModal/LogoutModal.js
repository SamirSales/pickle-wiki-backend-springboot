
import React from 'react';
import Backdrop from '../../Backdrop/Backdrop';
import Aux from '../../../../hoc/Aux';

import './LogoutModal.css';

const logoutModal = ( props ) => {

    return (
        <Aux>
            <Backdrop active={props.active} click={props.cancel} />
            
            <div className="modal-content" 
                style={{ display: props.active ? 'block' : 'none' }}> 
                <h3>Fazer logout</h3>
                <p>Tem certeza que deseja fazer logout?</p>
                <div className="buttons">
                    <button className="cancel" onClick={props.cancel}>Não</button>
                    <button onClick={props.confirm}>Sim</button>                    
                </div>
            </div>
        </Aux>        
    )
};
export default logoutModal;
