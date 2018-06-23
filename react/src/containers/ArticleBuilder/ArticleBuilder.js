import React, { Component } from 'react';

import {insertArticle, getArticleByUrl} from '../../axios-orders';
import TextEditor from '../../components/TextEditor/TextEditor';
import Aux from '../../hoc/Aux/Aux';
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
        // ---------------------
        errorModal: {
            title: 'Error',
            message: 'Não foi possível se conectar ao servidor.',
            active: false
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

    submit = () =>{
        // console.log("submit", this.state);

        // verify empty field
        if(this.state.title.trim() !== '' && this.state.context.trim() !== '' &&
            this.state.body.trim() !== ''){
            
            // mount url
            const url = this.getURLFormat(this.state.title) + '-' 
                + this.getURLFormat(this.state.context);
            console.log('url', url);

            const article = {
                id: this.state.id,
                title: this.state.title,
                context: this.state.context,
                body: this.state.body,
                url: url,
                pictures: this.state.pictures,
                lastEditor: this.state.lastEditor,
                views: this.state.views
            };

            // verify url
            getArticleByUrl(url).then(res =>{
                if(res.data === ""){

                    insertArticle(article).then(res =>{
                        showSnackBar('Artigo salvo com sucesso!');
                        this.props.history.push({pathname: '/article/' + url});
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
            showSnackBar('Preencha todos os campos');
        }
        
        // confirm saving
        // save
    }

    cancel = () =>{
        console.log("cancelar");
    }

    imageUpload = () =>{
        console.log("image upload");
    }
 
    render() {
        return (
            <Aux>
                <h1 className='simple-template-title'><i className="fa fa-edit"></i> {this.getTitle()}</h1>
                <p className='article-builder-p'><b>Título</b></p>
                <input className="form-input" placeholder='Insira um título...' onChange={this.onChangeTitle} />

                <p className='article-builder-p'><b>Contexto</b></p>
                <input className="form-input" placeholder='Insira um contexto...' 
                    style={{marginBottom: '20px'}} onChange={this.onChangeContext} />
                
                {/* <p className='article-builder-p'><b>Palavras chaves</b></p>
                <input className="form-input" style={{marginBottom: '20px'}} 
                    placeholder='Insira palavras chaves...' /> */}

                <TextEditor title={this.getTitle()} onChangeBody={this.onChangeBody}/>                
    
                <h2 className='article-builder-h2'>Imagens</h2>
                <button className='article-btn article-btn-topic'
                    onClick={this.imageUpload}>Fazer upload de imagem</button>
                <div className='article-builder-div-content' style={{marginTop: '10px'}}>
                    <p className='empty-content-message'>Nenhuma imagem foi importada para esse artigo.</p>
                </div>

                <h2 className='article-builder-h2'>Em fim!</h2>

                
                <div style={{marginTop: '10px'}}>
                    <button className='article-btn article-btn-submit' style={{marginRight: '10px'}}
                        onClick={this.submit}>Submeter</button>
                    <button className='article-btn article-btn-cancel'
                        onClick={this.cancel}>Descartar</button>
                </div>
    
                <br/><br/><br/><br/>
            </Aux>
        )
    }    
}

export default ArticleBuilder;
