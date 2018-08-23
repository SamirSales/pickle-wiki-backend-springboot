
import React from 'react';
import Modal from '../Modal';

const confirmModal = ( props ) => {

    return (
        <Modal active={props.active} 
            cancel={props.cancel} 
            marginTop={props.marginTop}
            marginLeft={props.marginLeft}>
            
            <h3 className="modal-confirm-title">{props.title}</h3>
            <p className="modal-confirm-msg">{props.question}</p>
            <div className="buttons">
                <button onClick={props.cancel}>Não</button>
                <button className="modal-confirm-btn-confirm" onClick={props.confirm}>Sim</button>                    
            </div>
        </Modal>        
    )
};

export default confirmModal;
