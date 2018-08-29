import React from 'react';
import ReactMarkdown from 'react-markdown';

const welcome = ( props ) => {

    const body = "# Bem-vindo ao Pickle Wiki \r"+
    "O Pickle Wiki é um projeto de enciclopédia *open source* de licença "+
    "[MIT](https://pt.wikipedia.org/wiki/Licen%C3%A7a_MIT), escrito de maneira colaborativa e baseado no sistema "+
    "[Wikipédia](https://www.wikipedia.org/).\r\r"+
    "Diferente do tradicional Wikipédia, o Pickle Wiki é uma ferramenta de pesquisa com foco em redes internas, "+
    "por isso o cadastro dos editores de artigos é feito por intermédio de um administrador pré-cadastrado.\r"+
    
    "## Origem do nome\r"+
    "O nome Pickle Wiki é uma paródia do nome *Wikipédia* combinada com a expressão *“Pickle Rick”*, "+
    "da animação americana [Rick and Morty](https://en.wikipedia.org/wiki/Rick_and_Morty) "+
    "(criada por *Justin Roiland* e *Dan Harmon*). No terceiro episódio da terceira temporada "+
    "dessa série, o personagem *Rick Sanchez* se transforma em um picles, na "+
    "intenção de escapar de uma sessão de terapia familiar. A genial sugestão do nome foi feita por "+
    "[John Soares](https://github.com/JohnSoares), durante um *brainstorm*.\r"+

    "## Manipulação de texto\r"+
    "A manipulação de texto do Pickle Wiki é feita por meio de *markdown*, uma linguagem de marcação "+
    "que permite a criação e o manuseio de textos de forma simples, ao mesmo tempo que mantém o padrão "+
    "de escrita.\r"+

    "## Principais tecnologias\r"+
    "| Tecnologia | Aplicação | Linguagem | Versão |\r"+
    "| --- | --- | --- |\r"+
    "| ReactJS | Front-end | JavaScript |16 |\r"+
    "| Spring Boot | Back-end | Java | 2.0.2 |\r"+
    "| PostgreSQL | Banco de dados  | SQL | 9.5.13 |\r";

    document.title = "Bem-vindo ao Pickle Wiki";

    return (
        <div className='text-editor-markdown'><ReactMarkdown source={body} /><br/></div>
    )
};
export default welcome;
