
import React from 'react';

import Aux from '../../hoc/Aux';
import './SimpleTemplate.css';

const simpleTemplate = ( props ) => {

    return (
        <Aux>
            <h1>{props.title}</h1>
            <p>{props.text}</p>
        </Aux>
    )
};
export default simpleTemplate;
