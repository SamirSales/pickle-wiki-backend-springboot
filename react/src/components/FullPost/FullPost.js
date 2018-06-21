import React, {Component} from 'react';

import {getArticleByUrl} from '../../axios-orders';
import ReactMarkdown from 'react-markdown';
import Aux from '../../hoc/Aux/Aux';

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
                <h1 className='simple-template-title'>{this.state.title}</h1>
                <div className='text-editor-markdown'><ReactMarkdown source={this.state.body} /></div>
            </Aux>
        );
    }
}

export default FullPost;