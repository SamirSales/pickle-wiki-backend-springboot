import React, {Component} from 'react';
import ReactMarkdown from 'react-markdown';
import TextEditor from '../TextEditor/TextEditor';
import Aux from '../../hoc/Aux/Aux';

class MarkDownHelp extends Component{

    state = {
        body: "# Ajuda (MarkDown) \r"+
            "Desenvolvido em 2004 por *John Gruber* e *Aaron Swartz* para simplificar a estruturação de um texto, "+
            "o **Markdown** é um sistema de formatação aberto que torna a escrita e a leitura mais simples. "+
            "Com uma codificação mínima, além de fácil, ele é visualmente mais \"limpo\" e pode ser convertido "+
            "facilmente para HTML.\r\r"+

            "Basicamente, ele marca alterações nos textos (subtítulos, negrito, itálico etc) apenas com os "+
            "símbolos do teclado, sem usar teclas de atalho, menus, selecionando o texto e sem aquele visual "+
            "complexo - para os que não estão acostumados - de HTML.\r"+
            
            "## Mostruário Pickle Wiki\r",
        
        sampleText: "# Live demo\r"+

        "Changes are automatically rendered as you type.\r"+        
        "* Implements [GitHub Flavored Markdown](https://github.github.com/gfm/)\r"+
        "* Renders actual, \"native\" React DOM elements\r"+
        "* Allows you to escape or skip HTML (try toggling the checkboxes above)\r"+
        "* If you escape or skip the HTML, no `dangerouslySetInnerHTML` is used! Yay!\r\r"+
        
        "## HTML block below\r"+        
        "<blockquote>This blockquote will change based on the HTML settings above.</blockquote>\r\r"+
        
        "## How about some code?\r"+
        "```js\r"+
        "var React = require('react');\r"+
        "var Markdown = require('react-markdown');\r"+
        
        "React.render(\r"+
            "  <Markdown source=\"# Your markdown here\" />,\r"+
            "document.getElementById('content')\r"+
            ");\r"+
            "```\r"+
        
            "Pretty neat, eh?\r\r"+
        
            "## Tables?\r"+        
            "| Feature | Support |\r"+
            "| ------ | ----------- |\r"+
            "| tables | ✔ |\r"+
            "| alignment | ✔ |\r"+
            "| wewt | ✔ |\r"+
        
            "## More info?\r"+        
            "Read usage information and more on [GitHub](//github.com/rexxars/react-markdown)\r"+
        
            "---------------\r"+
        
            "A component by [VaffelNinja](http://vaffel.ninja) / Espen Hovlandsdal"
    };

    onChangeBody = (event) => {
        this.setState( {
            sampleText: event.target.value
        })
    }

    render(){
        return (
            <Aux>
                <div className='text-editor-markdown'><ReactMarkdown source={this.state.body} /></div>
                
                <TextEditor 
                    title=""
                    content={this.state.sampleText}
                    onChangeBody={this.onChangeBody}/> 
            </Aux>
        );
    }
}
export default MarkDownHelp;