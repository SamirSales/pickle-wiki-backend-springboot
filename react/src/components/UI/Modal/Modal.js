import React from 'react';
import Backdrop from '../Backdrop/Backdrop';
import Aux from '../../../hoc/Aux';

import './Modal.css';

const modal = ( props ) => {

    return (
        <Aux>
            <Backdrop active={props.active} click={props.cancel} />
            
            <div className="modal-content" 
                style={{ display: props.active ? 'block' : 'none' }}>{props.children}</div>
        </Aux>        
    )
};

export default modal;
