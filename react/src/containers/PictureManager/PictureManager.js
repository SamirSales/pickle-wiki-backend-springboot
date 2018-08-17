import React, { Component } from 'react';

import * as axios from '../../axios-orders';

import { connect } from 'react-redux';
import Thumbnail from '../../components/Thumbnail/Thumbnail';
import ImageModal from '../../components/UI/Modal/ImageModal/ImageModal';
import Spinner from '../../components/UI/Spinner/Spinner';
import ConfirmModal from '../../components/UI/Modal/ConfirmModal/ConfirmModal';
import Aux from '../../hoc/Aux/Aux';
import './PictureManager.css';

import { showSnackBar } from '../Layout/Layout';

class PictureManager extends Component {

    state = {
        loading: false,
        pictures: [],
        activeImageModal: false,
        selectedPicture: null,
        selectedUploadFile: null,
        confirmModal: {
            title: '',
            question: '',
            active: false,
            clickActionConfirm: null
        },
        fileName: ""
    }

    componentDidMount(){
        this.refreshImages();
      }

    refreshImages = () => {
        this.setState({loading: true});
        // eslint-disable-next-line
        axios.getPictures(this.props.tkn).then(res => {
            // console.log("pictures", res.data);
            this.setState({pictures: res.data});
            this.setState({loading: false});
          }).catch(error => {
            console.log("error", error);
            this.setState({loading: false});
            //this.errorModal('Não foi possível carregar as imagens.');
          });
    }

    imageUpload = () => {
        if(this.state.selectedUploadFile){

            if(this.state.fileName.trim() !== ""){
                const fd = new FormData();
                fd.append('file', this.state.selectedUploadFile);
                fd.append('fileName', this.state.fileName);
                fd.append("pictureType", "DEFAULT");

                axios.addPicture(this.props.tkn, fd).then(res => {
                    this.refreshImages();
                    this.setState({fileName: ""});
                    showSnackBar("Upload realizado com sucesso");
                }).catch(err => {console.log("error", err)});
            }else{
                showSnackBar("Preencha o nome do arquivo de imagem.");
            }            
        }else{
            showSnackBar("Nenhum arquivo foi selecionado.");
        }
    }

    fileSelectedHandler = event => {
        this.setState({
            selectedUploadFile: event.target.files[0],
            fileName: event.target.files[0].name.split('.').slice(0, -1).join('.')
        });
    }

    imageModal = (picture) => {
        this.setState({
            activeImageModal: true,
            selectedPicture: picture,
        });
    }
 
    /* Confirm Modal */

    cancelConfirmModal = () =>{
        this.setState({
            confirmModal:{active: false}
        })
    }

    deleteSelectedPicture = () =>{
        axios.deletePicture(this.props.tkn, this.state.selectedPicture.id).then(res =>{
            this.refreshImages();
            this.cancelConfirmModal();
            showSnackBar("Imagem removida com sucesso!");
        }).catch(err => {
            console.log("error", err);
        });
    }

    /* ImageModal */

    onConfirmDeleteImageModal = () =>{
        this.cancelImageModal();
        this.setState({
            confirmModal: {
                title: this.state.selectedPicture.label,
                question: 'Tem certeza que deseja remover essa image?',
                active: true,
                clickActionConfirm: this.deleteSelectedPicture.bind(this)
            }
        });
    }

    cancelImageModal = () => {
        this.setState({
            activeImageModal: false
        });
    }

    onFileNameChange = event => {
        this.setState({
            fileName: event.target.value
        });
    }

    render() {
        console.log("this.state.pictures", this.state.pictures);

        let thumbnails = this.state.pictures.map(pic => {
            return (
                <Thumbnail key={pic._id} 
                    fileName={pic._id+"."+pic.fileExtension} 
                    alt={pic.name} 
                    onClick={this.imageModal.bind(this, pic)} />);
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
                    confirm={this.state.confirmModal.clickActionConfirm}
                    cancel={this.cancelConfirmModal.bind(this)} />

                <ImageModal active={this.state.activeImageModal} 
                    title={this.state.selectedPicture ? this.state.selectedPicture.label : null}
                    cancel={this.cancelImageModal}
                    src={this.state.selectedPicture ? this.state.selectedPicture._id+'.'+this.state.selectedPicture.fileExtension : null}
                    clickDelete={this.onConfirmDeleteImageModal.bind(this)} />

                <div className='text-editor-markdown'>
                    <h1 className='simple-template-title'><i className="fa fa-image"></i> Imagens</h1>
                </div>  

                <div style={{marginTop: '10px'}}>
                    {thumbnails}
                </div>           

                <br/>

                <div className="picture-manager-upload-div">
                    <input type="text" className="picture-manager-input-file-name"
                        value={this.state.fileName} 
                        placeholder="Nome do arquivo de imagem"
                        onChange={this.onFileNameChange} />
                        
                        <br/> 
                    <input className='fileContainer' type="file" onChange={this.fileSelectedHandler} />
                    <button className='article-btn article-btn-topic' onClick={this.imageUpload} >Enviar imagem</button>
                </div>
                
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

