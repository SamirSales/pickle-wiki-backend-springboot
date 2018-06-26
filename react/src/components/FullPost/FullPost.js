import React, {Component} from 'react';

import {getArticleByUrl} from '../../axios-orders';
import ReactMarkdown from 'react-markdown';
import Aux from '../../hoc/Aux/Aux';
import './FullPost.css';

class FullPost extends Component{

    state = {
        title: 'Artigo',
        body: 'Carregando artigo...'
    }

    componentDidMount(){        
        

        const url = this.props.match.params.tag;
        
        getArticleByUrl(url).then(res => {
            console.log(res);
            this.setState({
                title: res.data.title,
                body: res.data.body
            })
        }).catch(err =>{
            console.log(err);
        });
    }

    render(){
        return (
            <Aux>
                <div style={{position: 'relative'}}>
                    <h1 className='simple-template-title'>{this.state.title}</h1>

                    <div className="full-post-icon-link-div">
                        <i className="full-post-icon-link fa fa-edit"> editar artigo</i>
                        <i className="full-post-icon-link fa fa-trash"> excluir artigo</i>
                    </div>
                    
                    <div className='text-editor-markdown'><ReactMarkdown source={this.state.body} /></div> 
                </div>
                                 
            </Aux>
        );
    }
}

export default FullPost;