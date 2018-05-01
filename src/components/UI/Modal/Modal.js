
import React from 'react';

import './Modal.css';

const modal = ( props ) => {

    return (
        <div className={props.active === true ? 'modal' : ''}>
        
            <div className="modal-content" 
                style={{
                    display: props.active ? 'block' : 'none',
                }}> 
                <h3>{props.title}</h3>
                <p>{props.message}</p>
                <div className="buttons">
                    <button className="cancel" onClick={props.cancel}>Não</button>
                    <button onClick={props.confirm}>Sim</button>                    
                </div>
            </div>

        </div>
    )
};
export default modal;
