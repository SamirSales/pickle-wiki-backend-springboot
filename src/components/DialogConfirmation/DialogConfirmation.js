
import React from 'react';

import './DialogConfirmation.css';

const dialogConfirmation = ( props ) => {

    let dialog = null;

    if(props.active === true){
        dialog = (
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
        <div className={props.active === true ? 'modal' : ''}>{dialog}</div>
    )
};
export default dialogConfirmation;
