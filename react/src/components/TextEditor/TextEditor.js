import React, { Component } from 'react';

// import CKEditor from "react-ckeditor-component";
import ReactMarkdown from 'react-markdown';
import './TextEditor.css';
 
class TextEditor extends Component {

    state = {
        content: '',
        shownPreview: true
    }
 
    componentDidMount(){
        // console.log('this.props.content',this.props);
        if(this.props.content){
            this.setState({
                content: this.props.content
            });
        }
        
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
        });

        this.props.onChangeBody(event);
    }

    getContent(){
        return this.props.title.trim() === '' ? this.state.content 
            : '# '+this.props.title.trim()+'\n'+this.state.content;
    }
 
    render() {

        let page = null;

        if(this.state.shownPreview){
            page = <div className='text-editor-markdown'><ReactMarkdown source={this.getContent()} /></div>;
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
                        className={!this.state.shownPreview ? 'text-editor-div-tab-selected': ''}>
                        <i className="fa fa-edit"></i></button>
                    <button onClick={this.turnOnPreview}
                        className={this.state.shownPreview ? 'text-editor-div-tab-selected': ''} >
                        <i className="fa fa-eye"></i></button>
                    
                    <a className='text-editor-link-help' target="_blank" 
                        href="https://rexxars.github.io/react-markdown/"
                        rel="noopener noreferrer">
                        <i className="fa fa-question-circle"></i></a>
                </div>

                <div className='text-editor-div-content'>
                    {page}
                </div>
                
            </div>
        )
    }
}

export default TextEditor;