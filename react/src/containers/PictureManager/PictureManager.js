import React, { Component } from 'react';

import * as axios from '../../axios-orders';

import { connect } from 'react-redux';
import Thumbnail from '../../components/Thumbnail/Thumbnail';
import ImageModal from '../../components/UI/Modal/ImageModal/ImageModal';
import Spinner from '../../components/UI/Spinner/Spinner';
import ConfirmModal from '../../components/UI/Modal/ConfirmModal/ConfirmModal';
import EditImageModal from '../../components/UI/Modal/EditImageModal/EditImageModal';
import Aux from '../../hoc/Aux/Aux';
import './PictureManager.css';

// import { showSnackBar } from '../Layout/Layout';

class PictureManager extends Component {

    state = {
        loading: false,
        pictures: [],
        activeImageModal: false,
        selectedPicture: null,
        confirmModal: {
            title: '',
            question: '',
            active: false
        }
    }

    componentDidMount(){
        this.setState({loading: true});
        // eslint-disable-next-line
        axios.getPictures(this.props.tkn).then(res => {
          console.log("pictures", res.data);
          this.setState({pictures: res.data});
          this.setState({loading: false});
        }).catch(error => {
          console.log("error", error);
          this.setState({loading: false});
          this.errorModal('Não foi possível carregar as imagens.');
        });
      }

    imageUpload = () =>{
        console.log("image upload");
        const fd = new FormData();
        fd.append('image', this.state.selectedFile, this.state.selectedFile.name);

        //post request here...
    }

    fileSelectedHandler = event => {
        this.setState({
            selectedFile: event.target.files[0]
        });
    }

    imageModal = (picture) =>{
        // console.log("picture", picture);
        this.setState({
            activeImageModal: true,
            selectedPicture: picture,
        });
    }
 
    cancelImageModal = () => {
        this.setState({
            activeImageModal: false
        });
    }

    onConfirmImageModal = (pic) =>{
        console.log("image", pic);
    }

    onConfirmEditImageModal = () =>{
        console.log("Edit", this.state.selectedPicture);
    }

    onConfirmDeleteImageModal = () =>{
        console.log("Delete", this.state.selectedPicture);
    }

    render() {

        let thumbnails = this.state.pictures.map(pic => {
            return <Thumbnail key={pic.id} fileName={pic.fileName} 
                alt={pic.label} onClick={this.imageModal.bind(this, pic)} />;
        });

        if(this.state.pictures.length === 0){
            thumbnails = <p className='empty-content-message'>Nenhuma imagem foi importada para esse artigo.</p>;
        }

        return (
            <Aux>
                <Spinner
                    marginLeft='calc(50% - 404px)'
                    marginTop='8%'
                    active={this.state.loading} />

                <ConfirmModal 
                    title={this.state.selectedPicture ? this.state.selectedPicture.fileName : null}
                    question={this.state.confirmModal.question}
                    active={this.state.confirmModal.active}
                    marginLeft='calc(50% - 404px)'
                    marginTop='10%'
                    confirm={this.removeUser} 
                    cancel={this.closeConfirmModal} />

                <ImageModal active={this.state.activeImageModal} 
                    title={this.state.selectedPicture ? this.state.selectedPicture.label : null}
                    cancel={this.cancelImageModal}
                    src={this.state.selectedPicture ? this.state.selectedPicture.fileName : null}
                    clickEdit={this.onConfirmEditImageModal.bind(this)}
                    clickDelete={this.onConfirmDeleteImageModal.bind(this)} />

                <div className='text-editor-markdown'>
                    <h1 className='simple-template-title'><i className="fa fa-image"></i> Imagens</h1>
                </div>  

                <div style={{marginTop: '10px'}}>
                    {thumbnails}
                </div>           

                <br/>
                <input className='fileContainer' type="file" onChange={this.fileSelectedHandler} />
                <button className='article-btn article-btn-topic' onClick={this.imageUpload} >Carregar imagem</button>
            </Aux>
        )
    }    
}

const mapStateToProps = state => {
    return{
        usr: state.usr.user,
        appName: state.app.appName,
        tkn: state.usr.token
    };
}

export default connect(mapStateToProps)(PictureManager);

