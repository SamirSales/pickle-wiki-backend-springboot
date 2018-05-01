
import React from 'react';

import './Modal.css';

const modal = ( props ) => {

    let modal = null;

    if(props.active === true){
        modal = (
            <div className="modal-content">
                <h3>{props.title}</h3>
                <p>{props.message}</p>
                <div className="buttons">
                    <button className="cancel" onClick={props.cancel}>Não</button>
                    <button onClick={props.confirm}>Sim</button>                    
                </div>
            </div>
        );
    }

    return (
        <div className={props.active === true ? 'modal' : ''}>{modal}</div>
    )
};
export default modal;
