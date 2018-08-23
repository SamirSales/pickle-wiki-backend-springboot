import React, { Component } from 'react';
import Backdrop from '../../Backdrop/Backdrop';
import Aux from '../../../../hoc/Aux/Aux';
import Thumbnail from '../../../Thumbnail/Thumbnail';

import * as axios from '../../../../axios-orders';
import * as cookie from '../../../../cookie-handler';
import './ImagePickerModal.css';

class ImagePickerModal extends Component {

    state = {
        searchValue: "",
        thumbnails: null
    }

    componentDidUpdate(prevProps, prevState){

        if(!this.props.active && prevProps.active){
            this.setState({
                searchValue: "",
                thumbnails: null
            });

            document.getElementById('image-picker-modal-search-input').value = "";
        }
    }

    onChangeSearch = event => {
        const search = event.target.value;

        this.setState({
            searchValue: search
        });

        // console.log("search", search);

        if(search.trim() !== ''){
            axios.getPicturesBySearch(cookie.getToken(), search).then(res => {
                // console.log("pic search...", res.data);
                this.setState({thumbnails: res.data});
            }).catch(err => {
                console.log("error", err);
                this.setState({thumbnails: null});
            });
        }else{
            this.setState({thumbnails: null});
        }
    }

    render(){
        const display = this.props.active ? 'block' : 'none';

        let thumbnailComponents = <p className="no-picture-found-msg"><i>Nenhuma imagem foi encontrada.</i></p>;

        if(this.state.thumbnails && this.state.thumbnails.length > 0 ){
            thumbnailComponents = this.state.thumbnails.map(pic =>{
                return (
                    <Thumbnail key={pic._id} 
                        thumbFileName={pic.thumbFileName} 
                        fileName={pic.fileName}
                        alt={pic.name} 
                        onClick={this.props.thumbClick.bind(this, pic)} />);
            });
        }

        return (
            <Aux>
                <Backdrop active={this.props.active} click={this.props.cancel} />
                
                <div className="image-picker-modal" style={{ display: display}} >

                    <input  id="image-picker-modal-search-input" 
                        type="text" placeholder="Pesquisa de imagem..." 
                        onChange={this.onChangeSearch} 
                        value={this.searchValue} />

                    <div className="image-picker-thumbnails-content">{thumbnailComponents}</div>
                </div>
            </Aux>        
        );
    }
}

export default ImagePickerModal;
