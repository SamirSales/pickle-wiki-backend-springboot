import React from 'react';

import './Thumbnail.css';

const thumbnail = ( props ) => {

    const source = "file:///home/samir/Imagens/test/"+props.fileName;

    return (
        <div className="thumbnail-image">
            <img src="data:image/jpeg;base64,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" 
                alt={props.alt} height="90" width="90" />
            {/* <img src={source} alt={props.alt} height="42" width="42" /> */}
        </div>
    )
};
export default thumbnail;
