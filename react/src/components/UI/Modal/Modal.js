import React from 'react';
import Backdrop from '../Backdrop/Backdrop';
import Aux from '../../../hoc/Aux/Aux';

import './Modal.css';

const modal = ( props ) => {

    const marginLeft = props.marginLeft ? props.marginLeft :'0px';
    const marginTop = props.marginTop ? props.marginTop :'0px';
    const display = props.active ? 'block' : 'none';

    return (
        <Aux>
            <Backdrop active={props.active} click={props.cancel} />
            
            <div className="modal-content" 
                style={{ display: display, 
                    marginLeft: marginLeft, 
                    marginTop: marginTop}} >{props.children}</div>
        </Aux>        
    )
};

export default modal;
