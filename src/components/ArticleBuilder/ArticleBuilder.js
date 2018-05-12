
import React, { Component } from 'react';

import Aux from '../../hoc/Aux';
import './ArticleBuilder.css';

import CKEditor from "react-ckeditor-component";

class ArticleBuilder extends Component {

    constructor(props) {
        super(props);
        this.updateContent = this.updateContent.bind(this);

        this.state = {
            resumeContent: 'content',
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
    
                <p className='article-builder-p'><b>Resumo <span className='needed'>*</span></b></p>
                <CKEditor 
                    activeClass="p10" 
                    content={this.state.resumeContent} 
                    events={{"change": this.onResumeContentChange }} />
    
                <h2 className='article-builder-h2'>Tópicos & Templates</h2>
    
                <button>Novo Tópico</button>
    
                <button>Submeter</button>
                <button>Descartar</button>
    
                <br/><br/><br/><br/>
            </Aux>
        )
    }

    
}


export default ArticleBuilder;
