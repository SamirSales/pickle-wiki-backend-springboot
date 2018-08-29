import React, {Component} from 'react';
import ReactMarkdown from 'react-markdown';
import TextEditor from '../TextEditor/TextEditor';
import Aux from '../../hoc/Aux/Aux';

import earthImage from '../../assets/img/earth.png';

class MarkDownHelp extends Component{

    componentDidMount(){
        document.title = "MarkDown - Pickle Wiki";
    }

    state = {
        body: "# Markdown \r"+
            "Desenvolvido em 2004 por *John Gruber* e *Aaron Swartz* para simplificar a estruturação de um texto, "+
            "o **Markdown** é um sistema de formatação aberto que torna a escrita e a leitura mais simples. "+
            "Com uma codificação mínima, além de fácil, ele é visualmente mais \"limpo\" e pode ser convertido "+
            "facilmente para HTML.\r"+
            
            "## Demonstração\r",
        
        sampleText: "# Título\r"+

        "Parágrafo normal aqui...\r\r"+ 
        "## Listas\r"+       
        "* Primeiro item com [link do Google](https://google.com) para exemplo;\r"+
        "* Segundo item com *itálico* para _exemplo_;\r"+
        "* Terceiro item com **negrico** para __exemplo__;\r"+
        "* Quarto item com ***negrito*** e ___itálico___ para exemplo;\r\r"+

        "### Itens numerados\r"+
        "1. Primeiro item com [link do Google](https://google.com) para exemplo;\r"+
        "2. Segundo item com *itálico* para _exemplo_;\r"+
        "3. Terceiro item com **negrico** para __exemplo__;\r"+
        "4. Quarto item com ***negrito*** e ___itálico___ para exemplo;\r\r"+

        "### Itens marcados ou não\r"+
        "- [x] Primeiro item com [link do Google](https://google.com) para exemplo;\r"+
        "- [ ] Segundo item com *itálico* para _exemplo_;\r"+
        "- [x] Terceiro item com **negrico** para __exemplo__;\r"+
        "- [ ] Quarto item com ***negrito*** e ___itálico___ para exemplo;\r\r"+
        
        "## Citações\r"+        
        "> *\"A vingança nunca é plena, mata a alma e envenena.\"* **MADRUGA, Seu.**\r\r"+
        
        "## Códigos\r"+
        "`Sample of code`\r\r"+

        "```\r"+
        "sampleFunction = () => {\r"+
        "    var x = 3;\r"+
        "    var y = 7;\r"+
        "    return x + y;\r"+
        "}\r"+
        "```\r\r"+
    
        "## Tabelas\r"+        
        "| Cabeçalho 1 | Cabeçalho 2 | Cabeçalho 3 |\r"+
        "| --- | --- | --- |\r"+
        "| exemplo 1 | Exemplo de coluna um pouco mais larga | ✔ |\r"+
        "| exemplo 2 | exemplo 4  | ✔ |\r"+
        "| exemplo 3 | exemplo 5  | ✔ |\r\r"+
    
        "## Imagens\r"+        
        "![Bem vindo]("+earthImage+")\r\r"+

        "---------------\r"+
    
        "Isso é tudo!"
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
                    value={this.state.sampleText}
                    onChangeBody={this.onChangeBody}/> 
                
                <br />
            </Aux>
        );
    }
}
export default MarkDownHelp;