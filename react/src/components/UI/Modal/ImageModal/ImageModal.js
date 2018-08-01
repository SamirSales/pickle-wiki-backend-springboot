
import React,{PureComponent} from 'react';
import Aux from '../../../../hoc/Aux/Aux';
import Backdrop from '../../Backdrop/Backdrop';
import * as config from '../../../../config';

class ImageModal extends PureComponent {

    onStopPropagation = event =>{
        event.stopPropagation();
        event.nativeEvent.stopImmediatePropagation();
    }

    render(){
        const src = config.URL_IMAGES+"/"+this.props.src;
        const marginLeft = 'calc(50% - 300px)';
        const marginTop = this.props.marginTop ? this.props.marginTop :'0px';
        const display = this.props.active ? 'block' : 'none';

        return (
            <Aux>
                <Backdrop active={this.props.active} click={this.props.cancel} />
                
                <div className="image-modal-content" 
                    style={{display: display, marginLeft: marginLeft, marginTop: marginTop}} >

                    <img src={src} style={{position: 'fixed', zIndex: '120'}} height="400" width="400" 
                        alt={this.props.title}/>    
                </div>
            </Aux> 
        );
    }
}

export default ImageModal;
