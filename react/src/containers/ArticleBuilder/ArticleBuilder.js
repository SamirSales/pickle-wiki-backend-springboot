import React, { Component } from 'react';

import { connect } from 'react-redux';
import TextEditor from '../../components/TextEditor/TextEditor';
import ImagePickerModal from '../../components/UI/Modal/ImagePickerModal/ImagePickerModal';
import Aux from '../../hoc/Aux/Aux';

import * as axios from '../../axios-orders';
import * as config from '../../config';

import './ArticleBuilder.css';

import { showSnackBar } from '../Layout/Layout';

class ArticleBuilder extends Component {

    state = {
        // data of article -----
        id: null,
        title: '',
        context: '',
        body: '',
        pictures: [],
        views: 0,
        url: '',
        lastEditor: '',
        fullArticle: null,
        selectedFile: null,
        // ---------------------
        errorModal: {
            title: 'Error',
            message: 'Não foi possível se conectar ao servidor.',
            active: false
        },
        imagePickerModalActive: false,
    }

    componentDidMount(){        
        this.setFieldsInit();
    }

    setFieldsInit = () => {
        // verify if it is an article edition...
        if(this.props.location.state && this.props.location.state.article){
            document.title = "Edição de Artigo - Pickle Wiki";

            const article = this.props.location.state.article;

            // replace tags by URLs 
            const re = new RegExp(config.TAG_OF_ARTICLE_IMAGE, "g");
            const bodySet = article.body.replace(re, config.URL_IMAGES);

            this.setState({
                id: article.id,
                title: article.title,
                body: bodySet,
                context: article.context,
                pictures: [],
                lastEditor: '',
                fullArticle: article
            });

            document.getElementById("input-article-title").value = article.title;
            document.getElementById("input-article-context").value = article.context;
        }else{
            // in case of new article
            document.title = "Novo de Artigo - Pickle Wiki";
        }
    }

    onChangeTitle = (event) => {
        this.setState( {
            title: event.target.value
        })
    }

    onChangeContext = (event) => {
        this.setState( {
            context: event.target.value
        })
    }

    onChangeBody = (event) => {
        this.setState( {
            body: event.target.value
        })
    }

    getTitle(){
        return this.state.title.trim() === '' ? 'Novo artigo' : this.state.title.trim();
    }

    getURLFormat(text){
        const toLowerCase = text.toLowerCase();
        const words = toLowerCase.split(" ");
    
        var i, result = "";
        for(i=0; i<words.length; i++){
            result += words[i];
            
            if(i+1 < words.length){
                result += "-";
            }
        }
        return result;
    }

    modalImagePicker = () =>{
        this.setState({
            imagePickerModalActive: true
        });
    }

    closeModalImagePicker = () =>{
        this.setState({
            imagePickerModalActive: false
        });
    }

    imagePickerModalThumbClick = (picture) =>{
        
        const updatedBody = this.state.body 
            + "\r!["+picture.name+"](" + config.URL_IMAGES
            + "/" + picture.fileName + ")";

        this.setState({
            body: updatedBody
        });

        this.closeModalImagePicker();
    }

    submit = () => {
        // verify empty field
        if(this.state.title.trim() !== '' && this.state.context.trim() !== '' &&
            this.state.body.trim() !== ''){
            
            // mount url
            const url = this.getURLFormat(this.state.title) + '-' 
                + this.getURLFormat(this.state.context);

            // replace URLs by tags
            const re = new RegExp(config.URL_IMAGES, "g");
            const bodySet = this.state.body.replace(re, config.TAG_OF_ARTICLE_IMAGE);

            const article = {
                id: this.state.id,
                title: this.state.title,
                context: this.state.context,
                body: bodySet,
                url: url,
                pictures: this.state.pictures,
                lastEditor: this.state.lastEditor,
                views: this.state.views
            };

            // verify if it is an edition
            if(this.state.fullArticle){
                // editing article...
                // verify url
                axios.getArticleByUrl(url).then(res =>{
                    if(res.data === "" || res.data.id === this.state.fullArticle.id){

                        axios.putArticle(article, this.props.tkn).then(res =>{
                            showSnackBar('Artigo salvo com sucesso!');
                            this.props.history.push({pathname: config.URL_HOME_PAGE+'/article/' + encodeURIComponent(url)});
                        }).catch(err => {
                            console.log("err...", err);
                        });
                    }else{
                        showSnackBar('Um artigo com o mesmo título e contexto já foi adicionado.');
                    }
                }).catch(err => {
                    console.log("err...", err);
                });
            }else{
                // new article...
                // verify url
                axios.getArticleByUrl(url).then(res =>{
                    if(res.data === ""){

                        axios.insertArticle(article, this.props.tkn).then(res =>{
                            showSnackBar('Artigo salvo com sucesso!');
                            this.props.history.push({pathname: config.URL_HOME_PAGE+'/article/' + encodeURIComponent(url)});
                        }).catch(err => {
                            console.log("err...", err);
                        });
                    }else{
                        showSnackBar('Um artigo com o mesmo título e contexto já foi adicionado.');
                    }
                }).catch(err => {
                    console.log("err...", err);
                });
            }
            
        }else{
            showSnackBar('Preencha todos os campos');
        }
        
        // confirm saving
        // save
    }

    cancel = () =>{
        console.log("cancelar");
    }
 
    render() {

        // console.log("this.props", this.props);
        const textEditorContent = this.props.location.state ? this.props.location.state.article.body : '';
        
        return (
            <Aux>
                <ImagePickerModal 
                    active={this.state.imagePickerModalActive}
                    thumbClick={this.imagePickerModalThumbClick}
                    cancel={this.closeModalImagePicker} />

                <div className='text-editor-markdown'>
                    <h1 className='simple-template-title'><i className="fa fa-edit"></i> {this.getTitle()}</h1>
                </div>
                <p className='article-builder-p'><b>Título</b></p>
                <input id='input-article-title' className="form-input" 
                    placeholder='Insira um título...' onChange={this.onChangeTitle} />

                <p className='article-builder-p'><b>Contexto</b></p>
                <input id='input-article-context' className="form-input" placeholder='Insira um contexto...' 
                    style={{marginBottom: '20px'}} onChange={this.onChangeContext} />

                <TextEditor 
                    title={this.getTitle()} 
                    content={textEditorContent}
                    onChangeBody={this.onChangeBody}
                    value={this.state.body}
                    onImageClick={this.modalImagePicker}
                    linkHelp="true"/>                
                
                <div style={{marginTop: '10px'}}>
                    <button className='article-btn article-btn-submit' style={{marginRight: '10px'}}
                        onClick={this.submit}>Submeter</button>
                    {/* <button className='article-btn article-btn-cancel'
                        onClick={this.cancel}>Descartar</button> */}
                </div>
    
                <br/><br/><br/><br/>
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

export default connect(mapStateToProps)(ArticleBuilder);

