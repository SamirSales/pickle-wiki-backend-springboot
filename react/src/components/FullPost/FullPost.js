import React, {Component} from 'react';

import {getArticleByUrl} from '../../axios-orders';

import Aux from '../../hoc/Aux/Aux';

class FullPost extends Component{

    state = {
        title: 'Artigo',
        body: 'Carregando artigo...'
    }

    componentDidMount(){
        
        const url = this.props.match.params.tag;
        console.log("url", url);
        
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
                <p className='simple-template-p'>{this.state.body}</p>
            </Aux>
        );
    }
}

export default FullPost;