
import React, { Component } from 'react';

import Aux from '../../hoc/Aux';
import './ArticleBuilder.css';

import CKEditor from "react-ckeditor-component";

class ArticleBuilder extends Component {

    constructor(props) {
        super(props);
        this.updateContent = this.updateContent.bind(this);

        this.state = {
            resumeContent: '',
        }
    }
 
    updateContent(newContent) {
        this.setState({
            resumeContent: newContent
        })
    }
    
    onResumeContentChange = (evt) => {
      var newContent = evt.editor.getData();
      this.setState({
        resumeContent: newContent
      })

      console.log(newContent);
    }

    render() {
        return (
            <Aux>
                <h1 className='simple-template-title'><i className="fa fa-edit"></i> Novo artigo</h1>
                <p className='article-builder-p'><b>Título <span className='needed'>*</span></b></p>
                <input className="form-input" placeholder='Insira um título...' />
    
                <p className='article-builder-p'><b>Palavras chaves</b></p>
                <input className="form-input" placeholder='Insira palavras chaves...' />
    
                <h2 className='article-builder-h2'>Imagens</h2>
                <button className='article-btn article-btn-topic'>Importar imagem</button>
                <div className='article-builder-div-content' style={{marginTop: '10px'}}>
                    <p className='empty-content-message'>Nenhuma imagem foi importada para esse artigo.</p>
                </div>

                {/* <p className='article-builder-p'><b>Resumo <span className='needed'>*</span></b></p> */}
                <h2 className='article-builder-h2'>Resumo</h2>
                <CKEditor 
                    activeClass="p10" 
                    content={this.state.resumeContent} 
                    events={{"change": this.onResumeContentChange }} />
    
                <h2 className='article-builder-h2'>Tópicos & Templates</h2>
                <button className='article-btn article-btn-topic'>Novo Tópico</button>
                <div className='article-builder-div-content' style={{marginTop: '10px'}}>
                <p className='empty-content-message'>Nenhum tópico foi adicionado para esse artigo.</p>
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
