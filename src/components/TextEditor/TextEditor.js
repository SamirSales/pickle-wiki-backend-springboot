import React, { Component } from 'react';

// import CKEditor from "react-ckeditor-component";
import ReactMarkdown from 'react-markdown';
import './TextEditor.css';
 
class TextEditor extends Component {

    state = {
        content: '# Título',
        shownPreview: false
    }
 
    updateContent(newContent) {
        this.setState({
            content: newContent
        });
    }

    turnOnPreview = () =>{
        this.setState({
            shownPreview: true
        });
    }

    turnOnCodeEdition = () =>{
        this.setState({
            shownPreview: false
        })
    }

    onChangeContent = (event) =>{
        this.setState( {
            content: event.target.value
        })
    }
 
    render() {

        let page = null;

        if(this.state.shownPreview){
            page = <ReactMarkdown source={this.state.content} />;
        }else{
            page = <textarea 
                className='text-editor-textarea' 
                onChange={this.onChangeContent}
                defaultValue={this.state.content} />;
        }

        return (
            <div className='text-editor-frame'>
                <div className='text-editor-div-tabs'>
                    <button onClick={this.turnOnCodeEdition} 
                        className={!this.state.shownPreview ? 'text-editor-div-tab-selected': ''}>Código</button>
                    <button onClick={this.turnOnPreview}
                        className={this.state.shownPreview ? 'text-editor-div-tab-selected': ''} >Visualização</button>
                </div>

                <div className='text-editor-div-content'>
                    {page}
                </div>
                
            </div>
        )
    }
}

export default TextEditor;