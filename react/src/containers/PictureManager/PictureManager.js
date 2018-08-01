import React, { Component } from 'react';

import * as axios from '../../axios-orders';

import { connect } from 'react-redux';
import Thumbnail from '../../components/Thumbnail/Thumbnail';
import ImageModal from '../../components/UI/Modal/ImageModal/ImageModal';
import Spinner from '../../components/UI/Spinner/Spinner';
import ConfirmModal from '../../components/UI/Modal/ConfirmModal/ConfirmModal';
import Aux from '../../hoc/Aux/Aux';
import './PictureManager.css';

// import { showSnackBar } from '../Layout/Layout';

class PictureManager extends Component {

    state = {
        loading: false,
        pictures: [],
        imageModal: {
            active: false,
            title: '',
            idImage: -1,
            src: ''
        },
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
        console.log("picture", picture);
        this.setState({
            imageModal: {
                active: true,
                title: picture.label,
                idImage: picture.id,
                src: picture.fileName
            }
        });
    }
 
    cancelImageModal = event =>{
        this.setState({
            imageModal: {
                active: false
            }
        });
    }

    onConfirmImageModal = (pic) =>{
        console.log("image", pic);
        // event.stopPropagation();
        // event.nativeEvent.stopImmediatePropagation();
    }

    render() {

        let thumbnails = this.state.pictures.map(pic => {
            return <Thumbnail key={pic.id} fileName={pic.fileName} 
                alt={pic.label} onClick={this.imageModal.bind(this, pic)}
                onDeleteClick={this.onConfirmImageModal.bind(this,pic)}/>;
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
                    title={this.state.confirmModal.title}
                    question={this.state.confirmModal.question}
                    active={this.state.confirmModal.active}
                    marginLeft='calc(50% - 404px)'
                    marginTop='10%'
                    confirm={this.removeUser} 
                    cancel={this.closeConfirmModal} />

                <ImageModal active={this.state.imageModal.active} 
                    cancel={this.cancelImageModal}
                    src={this.state.imageModal.src} />

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

