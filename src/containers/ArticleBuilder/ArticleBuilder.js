
import React, { Component } from 'react';
// import CKEditor from "react-ckeditor-component";

import TextEditor from '../../components/TextEditor/TextEditor';

import Aux from '../../hoc/Aux';
import './ArticleBuilder.css';

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

        // const ReactMarkdown = require('react-markdown');

        const input = '# This is a header\n\nAnd this is a paragraph\n\n ## other head \n\n [GitHub](//github.com/rexxars/react-markdown)';

        return (
            <Aux>
                <h1 className='simple-template-title'><i className="fa fa-edit"></i> Novo artigo</h1>
                <p className='article-builder-p'><b>Título <span className='needed'>*</span></b></p>
                <input className="form-input" placeholder='Insira um título...' />
                
                <p className='article-builder-p'><b>Palavras chaves</b></p>
                <input className="form-input" style={{marginBottom: '20px'}} 
                    placeholder='Insira palavras chaves...' />

                <TextEditor />                
    
                <h2 className='article-builder-h2'>Imagens</h2>
                <button className='article-btn article-btn-topic'>Importar imagem</button>
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
