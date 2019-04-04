import React, {Component} from 'react';

import {getArticleByUrl, deleteArticle} from '../../axios-orders';
import { showSnackBar } from '../../containers/Layout/Layout';

import ConfirmModal from '../UI/Modal/ConfirmModal/ConfirmModal';
import ReactMarkdown from 'react-markdown';
import Aux from '../../hoc/Aux/Aux';
import './FullPost.css';

import { connect } from 'react-redux';
import * as config from '../../config';

import defaultImageProfile from '../../assets/img/profile.png';

class FullPost extends Component{

    state = {
        articleId: null,
        title: 'Artigo',
        body: 'Carregando artigo...',
        confirmModal: {
            title: '',
            question: '',
            active: false
        },
        fullArticle: null,
        lastEditor: null,
    }

    componentDidMount(){               
        const url = this.props.match.params.tag;
        this.setPostByURL(url);
    }

    componentDidUpdate(prevProps, prevState) {
        // updating url...
        if(prevProps.match.params.tag !== this.props.match.params.tag){
            this.setPostByURL(this.props.match.params.tag);
        }
    }

    setPostByURL(url){
        getArticleByUrl(url).then(res => {
            // console.log(res.data);
            if(res.data !== ""){

                // replace tags by URLs 
                const re = new RegExp(config.TAG_OF_ARTICLE_IMAGE, "g");
                const bodySet = res.data.body.replace(re, config.URL_IMAGES);

                this.setState({
                    articleId: res.data.id,
                    title: res.data.title,
                    body: bodySet,
                    fullArticle: res.data,
                    lastEditor: res.data.lastEditor
                });

                document.title = res.data.title + " - Pickle Wiki";
            }else{
                this.notFoundArticle();
                document.title = "Artigo não encontrado - Pickle Wiki";
            }
            
        }).catch(err =>{
            console.log(err);
            this.notFoundArticle();
        });
    }

    notFoundArticle = () =>{
        this.setState({
            articleId: null,
            title: 'Artigo não encontrado',
            body: 'Não foi possível encontrar o artigo'
        });
    }

    onModalConfirmRemove = () =>{
        this.setState({
            confirmModal:{
                title: 'Remover \''+this.state.title+'\'',
                question: 'Tem certeza que deseja excluir esse artigo?',
                active: true
            }
        });
    }

    removeArticle = () =>{
        deleteArticle(this.state.articleId, this.props.tkn).then(res =>{
            this.props.history.push({pathname: config.URL_HOME_PAGE+'/welcome'});
            showSnackBar('Artigo excluído com sucesso!');

        }).catch(err => {
            console.log('error', err);
            showSnackBar('Não foi possível excluir o artigo.');
        });
    }

    closeConfirmModal = () =>{
        this.setState({
            confirmModal:{
                active: false
            }
        });
    }

    onEditClick = () =>{
        this.props.history.push({pathname: config.URL_HOME_PAGE+'/edit-article', 
            state: {
                article: this.state.fullArticle
            }
        });
    }

    formatDate(dateText) {
        const monthNames = [
          "janeiro", "fevereiro", "março",
          "abril", "maio", "junho", "julho",
          "agosto", "setembro", "outubro",
          "novembro", "dezembro"
        ];
      
        const date = new Date(dateText);
        const day = date.getDate();       
        const monthIndex = date.getMonth();
        const year = date.getFullYear();
        const hour = date.getHours();
        const minutes = date.getMinutes();
        const seconds = date.getSeconds();
      
        return day + ' de ' + monthNames[monthIndex] + ' de ' + year + ' às ' 
            + hour +':'+minutes+':'+seconds;
    }

    render(){
        let editorsButtons = null;

        if(this.props.usr){
            editorsButtons = (<div className="full-post-icon-link-div">
                <i className="full-post-icon-link fa fa-edit" onClick={this.onEditClick}> editar artigo</i>
                <i className="full-post-icon-link fa fa-trash" onClick={this.onModalConfirmRemove}> excluir artigo</i>
            </div>);
        }

        let imageEditor = null;

        if(this.state.fullArticle && this.state.fullArticle.lastEditor){
            if(this.state.fullArticle.lastEditor.pictureFileName){
                const srcImg = config.URL_IMAGES_PROFILE + '/' + this.state.fullArticle.lastEditor.pictureFileName;
                imageEditor = <img src={srcImg} alt={this.state.fullArticle.lastEditor.name} height="48" width="48"/>
            } else {
                imageEditor = <img src={defaultImageProfile} alt={this.state.fullArticle.lastEditor.name} height="48" width="48"/>
            }
        }

        let infoEdition = null;

        if(this.state.articleId){
            infoEdition = (
                <div className='full-post-div-editor'>
                    <div className="img-div">{imageEditor}</div>
                    <div className="editor-name-div">Editado por <span className="highlight">{this.state.lastEditor ? this.state.lastEditor.name : ''}</span></div>
                    <div className="date-article-div"><i>{this.state.fullArticle ? this.formatDate(this.state.fullArticle.updatedAt) : ''}</i></div>
                </div>
            );
        }

        return (
            <Aux>
                <ConfirmModal 
                    title={this.state.confirmModal.title}
                    question={this.state.confirmModal.question}
                    active={this.state.confirmModal.active}
                    marginLeft='calc(50% - 404px)'
                    marginTop='10%'
                    confirm={this.removeArticle} 
                    cancel={this.closeConfirmModal} />

                <div style={{position: 'relative'}}>
                    <div className='text-editor-markdown'>
                        <h1>{this.state.title}</h1>
                    </div>

                    {editorsButtons}
                    
                    <div className='text-editor-markdown'>
                        <ReactMarkdown source={this.state.body} />
                    </div> 

                    {infoEdition}
                </div>
                                 
            </Aux>
        );
    }
}

const mapStateToProps = state => {
    return{
        usr: state.usr.user,
        appName: state.app.appName,
        tkn: state.usr.token
    };
}

export default connect(mapStateToProps)(FullPost);
