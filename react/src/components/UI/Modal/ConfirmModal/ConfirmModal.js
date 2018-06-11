
import React from 'react';
import Backdrop from '../../Backdrop/Backdrop';
import Aux from '../../../../hoc/Aux';

import './ConfirmModal.css';

const confirmModal = ( props ) => {

    return (
        <Aux>
            <Backdrop active={props.active} click={props.cancel} />
            
            <div className="modal-content" 
                style={{ display: props.active ? 'block' : 'none' }}> 
                <h3>{props.title}</h3>
                <p>{props.question}</p>
                <div className="buttons">
                    <button className="cancel" onClick={props.cancel}>Não</button>
                    <button onClick={props.confirm}>Sim</button>                    
                </div>
            </div>
        </Aux>        
    )
};
export default confirmModal;
