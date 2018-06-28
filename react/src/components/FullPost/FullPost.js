import React, {Component} from 'react';

import {getArticleByUrl, deleteArticle} from '../../axios-orders';
import { showSnackBar } from '../../containers/Layout/Layout';

import ConfirmModal from '../UI/Modal/ConfirmModal/ConfirmModal';
import ReactMarkdown from 'react-markdown';
import Aux from '../../hoc/Aux/Aux';
import './FullPost.css';

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
        fullArticle: null
    }

    componentDidMount(){        
        const url = this.props.match.params.tag;
        
        getArticleByUrl(url).then(res => {
            // console.log(res);
            if(res.data !== ""){
                this.setState({
                    articleId: res.data.id,
                    title: res.data.title,
                    body: res.data.body,
                    fullArticle: res.data
                });
            }else{
                this.notFoundArticle();
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
        deleteArticle(this.state.articleId).then(res =>{
            this.props.history.push({pathname: '/welcome'});
            showSnackBar('Artigo excluído com sucesso!');

        }).catch(err => {
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
        this.props.history.push({pathname: '/edit-article', 
            state: {
                article: this.state.fullArticle
            }
        });
    }

    render(){
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
                    <h1 className='simple-template-title'>{this.state.title}</h1>

                    <div className="full-post-icon-link-div">
                        <i className="full-post-icon-link fa fa-edit" onClick={this.onEditClick}> editar artigo</i>
                        <i className="full-post-icon-link fa fa-trash" onClick={this.onModalConfirmRemove}> excluir artigo</i>
                    </div>
                    
                    <div className='text-editor-markdown'><ReactMarkdown source={this.state.body} /></div> 
                </div>
                                 
            </Aux>
        );
    }
}

export default FullPost;