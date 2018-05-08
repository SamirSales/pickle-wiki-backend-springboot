
import React from 'react';

import Aux from '../../hoc/Aux';
import './ArticleBuilder.css';

const articleBuilder = ( props ) => {

    return (
        <Aux>
            <h1 className='simple-template-title'><i class="fa fa-edit"></i> Novo artigo</h1>
            <p className='article-builder-p'>Título <span className='needed'>*</span></p>
            <input className="form-input" placeholder='Insira um título...' />

            <p className='article-builder-p'>Resumo <span className='needed'>*</span></p>
            <textarea className="form-input" placeholder='Insira um resumo...' rows="5" />

            <h2 className='article-builder-h2'>Tópicos & Templates</h2>
            <button><i class="fa fa-bookmark"></i> Adicionar Tópico</button>
        </Aux>
    )
};
export default articleBuilder;
