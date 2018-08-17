
import React,{PureComponent} from 'react';
import Aux from '../../../../hoc/Aux/Aux';
import Backdrop from '../../Backdrop/Backdrop';
import * as config from '../../../../config';
import './ImageModal.css';

class ImageModal extends PureComponent {

    render(){
        let src = null;

        if(this.props.src){
            src = config.URL_IMAGES+"/"+this.props.src;
        }

        const marginTop = this.props.marginTop ? this.props.marginTop :'0px';
        const display = this.props.active ? 'block' : 'none';

        return (
            <Aux>
                <Backdrop active={this.props.active} click={this.props.cancel} />
                
                <div className="image-modal-content" 
                    style={{display: display, marginTop: marginTop}} >

                    <div className="image-modal-content-img">
                        <img src={src} alt={this.props.title}/>
                    </div>

                    <input type="text" value={this.props.title} />                    

                    <div className="image-modal-buttons">
                        <div onClick={this.props.cancel}><i className="fa fa-edit"></i></div>
                        <div onClick={this.props.clickDelete}><i className="fa fa-trash"></i></div>       
                        <div onClick={this.props.cancel}><i className="fa fa-times"></i></div>
                    </div>
                </div>
            </Aux> 
        );
    }
}

export default ImageModal;
