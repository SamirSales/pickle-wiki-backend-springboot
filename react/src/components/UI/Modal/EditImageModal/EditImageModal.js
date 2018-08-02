import React from 'react';
import Modal from '../Modal';

import * as config from '../../../../config';
import "./EditImageModal.css";

const editImageModal = ( props ) => {

    const src = config.URL_IMAGES+"/"+props.src;
    // const marginLeft = 'calc(50% - 300px)';
    // const marginTop = props.marginTop ? props.marginTop :'0px';
    // const display = props.active ? 'block' : 'none';

    return (
        <Modal active={props.active} 
            cancel={props.cancel} 
            marginTop={props.marginTop}
            marginLeft={props.marginLeft}>
            
            <div className="edit-image-modal">
                <div className="div-image">
                    <img src={src} style={{position: 'fixed', zIndex: '120'}} height="200" width="200" 
                        alt={props.title}/> 
                </div>
                <div className="div-edit-content"></div>
            </div>
        </Modal>        
    )
};

export default editImageModal;
