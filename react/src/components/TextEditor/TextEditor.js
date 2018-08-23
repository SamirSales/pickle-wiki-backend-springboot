import React, { Component } from 'react';

import { Link } from 'react-router-dom';
import ReactMarkdown from 'react-markdown';
import './TextEditor.css';
 
class TextEditor extends Component {

    state = {
        content: '',
        shownPreview: true
    }
 
    componentDidMount(){
        if(this.props.content){
            this.setState({
                content: this.props.content
            });
        }      
    }

    componentDidUpdate(){
        if(this.state.content !== this.props.value){
            this.setState({
                content: this.props.value
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
        page = (<div className='text-editor-markdown'>
                    <ReactMarkdown source={this.getContent()} />
                </div>);

        }else{
            page = <textarea 
                className='text-editor-textarea' 
                onChange={this.onChangeContent}
                value={this.state.content} />;
        }

        let linkHelp = null;
        let linkButtonImagePicker = null;

        if(this.props.linkHelp){
            linkHelp = <Link className='text-editor-link-help' to='/mark-down-help' target="_blank">
                <i className="fa fa-question-circle"></i></Link>;

            linkButtonImagePicker = (
                <a className="text-editor-link-help" 
                    onClick={this.props.onImageClick}><i className="fa fa-image"></i></a>
            );
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

                    {linkHelp}
                    {linkButtonImagePicker}

                </div>

                <div className='text-editor-div-content'>
                    {page}
                </div>
                
            </div>
        )
    }
}

export default TextEditor;