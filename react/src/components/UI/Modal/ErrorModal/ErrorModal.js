
import React from 'react';
import Backdrop from '../../Backdrop/Backdrop';
import Aux from '../../../../hoc/Aux';

const errorModal = ( props ) => {

    return (
        <Aux>
            <Backdrop active={props.active} click={props.cancel} />
            
            <div className="user-editor-modal-content" 
                style={{ display: props.active ? 'block' : 'none' }}> 
                <h3>{props.title}</h3>
                <p>{props.message}</p>
            </div>
        </Aux>        
    )
};
export default errorModal;
