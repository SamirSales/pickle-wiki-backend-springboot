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
        const sourceThumb = config.URL_IMAGES+"/"+this.props.thumbFileName;

        return (
            <div className="thumbnail-image">    
                <div className="thumbnail-image-img" onClick={this.props.onClick}>
                    <img src={sourceThumb} alt={this.props.alt} />
                </div>         
                                
                <div className="thumbnail-image-label">
                    <a target="_blank" href={source}>{this.props.alt}</a>
                </div>
            </div>
        );
    }
}

export default Thumbnail;
