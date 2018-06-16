import React from 'react';
import Modal from '../Modal/Modal';
import './Spinner.css';

const spinner = (props) =>{
    return (
        <Modal active={props.active} 
            cancel={props.cancel} 
            marginTop={props.marginTop}
            marginLeft={props.marginLeft}>
            <div className="spinner">Loading...</div>
        </Modal>
    );
}

export default spinner;