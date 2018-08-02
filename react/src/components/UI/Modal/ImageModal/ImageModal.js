
import React,{PureComponent} from 'react';
import Aux from '../../../../hoc/Aux/Aux';
import Backdrop from '../../Backdrop/Backdrop';
import * as config from '../../../../config';
import './ImageModal.css';

class ImageModal extends PureComponent {

    render(){
        const src = config.URL_IMAGES+"/"+this.props.src;
        const marginTop = this.props.marginTop ? this.props.marginTop :'0px';
        const display = this.props.active ? 'block' : 'none';

        return (
            <Aux>
                <Backdrop active={this.props.active} click={this.props.cancel} />
                
                <div className="image-modal-content" 
                    style={{display: display, marginTop: marginTop}} >
                    <img src={src} height="350" width="350" alt={this.props.title}/>    
                    <h3>{this.props.title}</h3>

                    <div className="image-modal-buttons">
                        <div><i class="fa fa-edit"></i></div>
                        <div><i class="fa fa-trash"></i></div>
                        <div><i class="fa fa-times"></i></div>
                    </div>
                </div>
            </Aux> 
        );
    }
}

export default ImageModal;
