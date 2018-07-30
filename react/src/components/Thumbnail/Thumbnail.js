import React from 'react';

import './Thumbnail.css';

const thumbnail = ( props ) => {

    const source = "http://localhost:8080/pickle-wiki-image/"+props.fileName;

    return (
        <div className="thumbnail-image">            
            <img src={source} alt={props.alt} height="90" width="90" />
            <div className="delete-image">x</div>
        </div>
    )
};
export default thumbnail;
