
import React from 'react';

import './Tab.css';

const tab = ( props ) => {

    let theTab = null;

    if(props.active === 'true'){
      theTab = <div className="tab active">{props.title}</div>
    }else{
      theTab = <div className="tab">{props.title}</div>
    }

    return (
        <div>{theTab}</div>
    )
};
export default tab;
