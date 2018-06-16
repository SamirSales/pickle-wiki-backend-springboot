
import React from 'react';
import Modal from '../Modal';

const confirmModal = ( props ) => {

    return (
        <Modal active={props.active} cancel={props.cancel}>
            <h3>{props.title}</h3>
            <p>{props.question}</p>
            <div className="buttons">
                <button className="cancel" onClick={props.cancel}>Não</button>
                <button onClick={props.confirm}>Sim</button>                    
            </div>
        </Modal>        
    )
};

export default confirmModal;
