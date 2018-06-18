import React, { Component } from 'react';

import TextEditor from '../../components/TextEditor/TextEditor';
import Aux from '../../hoc/Aux/Aux';
import './ArticleBuilder.css';

class ArticleBuilder extends Component {

    state = {
        title: '',
        content: ''
    }

    onChangeTitle = (event) => {
        this.setState( {
            title: event.target.value
        })
    }

    getTitle(){
        return this.state.title.trim() === '' ? 'Novo artigo' : this.state.title.trim();
    }
 
    render() {
        return (
            <Aux>
                <h1 className='simple-template-title'><i className="fa fa-edit"></i> {this.getTitle()}</h1>
                <p className='article-builder-p'><b>Título <span className='needed'>*</span></b></p>
                <input className="form-input" placeholder='Insira um título...' onChange={this.onChangeTitle} />

                <p className='article-builder-p'><b>Contexto</b></p>
                <input className="form-input" placeholder='Insira um contexto...' />
                
                <p className='article-builder-p'><b>Palavras chaves</b></p>
                <input className="form-input" style={{marginBottom: '20px'}} 
                    placeholder='Insira palavras chaves...' />

                <TextEditor title={this.getTitle()} />                
    
                <h2 className='article-builder-h2'>Imagens</h2>
                <button className='article-btn article-btn-topic'>Fazer upload de imagem</button>
                <div className='article-builder-div-content' style={{marginTop: '10px'}}>
                    <p className='empty-content-message'>Nenhuma imagem foi importada para esse artigo.</p>
                </div>

                <h2 className='article-builder-h2'>Em fim!</h2>

                
                <div style={{marginTop: '10px'}}>
                    <button className='article-btn article-btn-submit' style={{marginRight: '10px'}}>Submeter</button>
                    <button className='article-btn article-btn-cancel'>Descartar</button>
                </div>
    
                <br/><br/><br/><br/>
            </Aux>
        )
    }    
}

export default ArticleBuilder;
