
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
                    <img src={src} alt={this.props.title}/>    
                    <h3>{this.props.title}</h3>

                    <div className="image-modal-buttons">
                        <div onClick={this.props.clickDelete}><i className="fa fa-trash"></i> Remover</div>       
                        <div onClick={this.props.cancel}><i className="fa fa-times"></i> Fechar</div>
                    </div>
                </div>
            </Aux> 
        );
    }
}

export default ImageModal;
