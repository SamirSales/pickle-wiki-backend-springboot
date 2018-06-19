import React, {Component} from 'react';

import Aux from '../../hoc/Aux/Aux';

class FullPost extends Component{

    state = {
        title: 'Artigo',
        body: 'Carregando artigo...'
    }

    componentDidMount(){
        console.log("test",this.props.match.params.tag);
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