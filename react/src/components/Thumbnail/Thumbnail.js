import React, {PureComponent} from 'react';

import * as config from '../../config';
import './Thumbnail.css';

class Thumbnail extends PureComponent {

    onStopPropagation = event =>{
        this.props.onDeleteClick(event);
        event.stopPropagation();
        event.nativeEvent.stopImmediatePropagation();
    }

    render(){
        const source = config.URL_IMAGES+"/"+this.props.fileName;

        return (
            <div className="thumbnail-image" onClick={this.props.onClick}>             
                <img src={source} alt={this.props.alt} height="90" width="90" />
            </div>
        );
    }
}

export default Thumbnail;
