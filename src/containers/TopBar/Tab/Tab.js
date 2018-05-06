
import React from 'react';

import './Tab.css';

const tab = ( props ) => {
    return (
      <div className={props.active ? 'tab active' : 'tab'}>{props.title}</div>
    )
};

export default tab;
