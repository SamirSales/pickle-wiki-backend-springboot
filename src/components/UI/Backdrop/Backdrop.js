
import React from 'react';

import './Backdrop.css';

const backdrop = ( props ) => {

    return (
        <div className={props.active === true ? 'backdrop' : ''}  
            onClick={props.click}></div>
    )
};
export default backdrop;
