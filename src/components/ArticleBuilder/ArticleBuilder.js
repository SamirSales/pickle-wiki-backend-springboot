
import React from 'react';

import Aux from '../../hoc/Aux';
import TemplateButton from '../../components/TemplateButton/TemplateButton';
import './ArticleBuilder.css';

import TextEditor from '../../components/TextEditor/TextEditor';

const articleBuilder = ( props ) => {

    return (
        <Aux>
            <h1 className='simple-template-title'><i className="fa fa-edit"></i> Novo artigo</h1>
            <p className='article-builder-p'><b>Título <span className='needed'>*</span></b></p>
            <input className="form-input" placeholder='Insira um título...' />

            <p className='article-builder-p'><b>Palavras chaves</b></p>
            <input className="form-input" placeholder='Insira palavras chaves...' />

            <p className='article-builder-p'><b>Resumo <span className='needed'>*</span></b></p>
            {/* <textarea className="form-input" placeholder='Insira um resumo...' rows="5" /> */}
            <TextEditor />

            <h2 className='article-builder-h2'>Tópicos & Templates</h2>
            {/* <TemplateButton title='Texto' type='text'/>
            <TemplateButton title='Imagem' type='image'/>
            <TemplateButton title='Texto + Imagem' type='text-image'/> */}

            <button>Novo Tópico</button>

        </Aux>
    )
};
export default articleBuilder;
