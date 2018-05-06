
import React from 'react';

import Aux from '../../hoc/Aux';
import './ArticleBuilder.css';

const articleBuilder = ( props ) => {

    return (
        <Aux>
            <h1>Novo artigo</h1>
            <p>Título <span className='needed'>*</span></p>
            <input className="form-input" placeholder='Insira um título...' />

            <p>Resumo <span className='needed'>*</span></p>
            <textarea className="form-input" placeholder='Insira um resumo...' rows="5" />

            <h2>Tópicos</h2>
            <button>Novo Tópico</button>
        </Aux>
    )
};
export default articleBuilder;
