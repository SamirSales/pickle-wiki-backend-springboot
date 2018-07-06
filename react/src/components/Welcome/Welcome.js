import React from 'react';
import ReactMarkdown from 'react-markdown';

import welcomeImage from '../../assets/img/pickle-rick.jpg';

const welcome = ( props ) => {

    const body = "# Bem vindo ao Pickle Wiki \r"+
    "A Pickle Wiki é um projeto de enciclopédia de licença livre, baseado no sistema "+
    "[Wikipédia](https://www.wikipedia.org/) e escrito de maneira colaborativa. "+
    "Seu código fonte encontra-se no github, atualmente, sob administração de seu criador, Samir Sales. "+
    "O Pickle Wiki é uma ferramenta de pesquisa com foco em redes internas.\r"+
    
    "## Tecnologias utilizadas\r"+
    "* ReactJS 16\r"+
    "* Spring Boot\r"+
    "* PostGreSQL\r"+

    "## Origem do nome\r"+
    "O nome Pickle Wiki é uma paródia do nome *Wikipédia* combinada com a expressão *“Pickle Rick”* "+
    "da animação americana [Rick and Morty](https://en.wikipedia.org/wiki/Rick_and_Morty) "+
    "(criada por *Justin Roiland* e *Dan Harmon*). No terceiro "+
    "episódio da terceira temporada o personagem *Rick Sanchez* se transforma em um picles, na "+
    "intenção de escapar de uma sessão de terapia familiar.\r"+
    "![Bem vindo]("+welcomeImage+")\r";

    return (
        <div className='text-editor-markdown'><ReactMarkdown source={body} /></div>
    )
};
export default welcome;