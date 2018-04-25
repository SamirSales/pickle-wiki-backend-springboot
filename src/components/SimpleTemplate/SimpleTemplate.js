
import React from 'react';

import './SimpleTemplate.css';

const simpleTemplate = ( props ) => {

    return (
        <div>
            <h1>{props.title}</h1>
            <p>{props.text}</p>
        </div>
    )
};
export default simpleTemplate;
