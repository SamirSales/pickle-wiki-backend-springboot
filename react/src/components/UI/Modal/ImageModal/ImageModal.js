
import React from 'react';
import Aux from '../../../../hoc/Aux/Aux';
import Backdrop from '../../Backdrop/Backdrop';
import * as config from '../../../../config';

const imageModal = ( props ) => {

    const src = config.URL_IMAGES+"/"+props.src;

    const marginLeft = 'calc(50% - 300px)';
    const marginTop = props.marginTop ? props.marginTop :'0px';
    const display = props.active ? 'block' : 'none';

    return (
        <Aux>
            <Backdrop active={props.active} click={props.cancel} />
            
            <div className="image-modal-content" 
                style={{ display: display, marginLeft: marginLeft, marginTop: marginTop}} >

                {/* <h3>{props.title}</h3> */}
                <img src={src} height="400" width="400" alt={props.title}/>    
            </div>
        </Aux> 
    )
};

export default imageModal;
