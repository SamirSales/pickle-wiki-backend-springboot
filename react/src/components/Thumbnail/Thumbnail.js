import React from 'react';

import * as config from '../../config';
import './Thumbnail.css';

const thumbnail = ( props ) => {

    const source = config.URL_IMAGES+"/"+props.fileName;

    return (
        <div className="thumbnail-image" onClick={props.onClick}>             
            <img src={source} alt={props.alt} height="90" width="90" />
            <div className="delete-image">x</div>
        </div>
    )
};
export default thumbnail;
