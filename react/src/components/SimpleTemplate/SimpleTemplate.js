import React from 'react';

import Aux from '../../hoc/Aux/Aux';
import './SimpleTemplate.css';

const simpleTemplate = ( props ) => {

    return (
        <Aux>
            <h1 className='simple-template-title'>{props.title}</h1>
            <p className='simple-template-p'>{props.text}</p>
        </Aux>
    )
};
export default simpleTemplate;
