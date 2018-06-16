
import React from 'react';
import Modal from '../Modal';

const errorModal = ( props ) => {

    return (
        <Modal active={props.active} 
            cancel={props.cancel}
            marginTop={props.marginTop}
            marginLeft={props.marginLeft} >
            
            <h3>{props.title}</h3>
            <p>{props.message}</p>

        </Modal>        
    )
};
export default errorModal;
