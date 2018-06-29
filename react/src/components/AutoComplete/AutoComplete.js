import React, {PureComponent} from 'react';

import Aux from '../../hoc/Aux/Aux';
import Backdrop from '../UI/Backdrop/Backdrop';
import './AutoComplete.css';

class AutoComplete extends PureComponent {

    state = {
        countries : ["Afghanistan","Brazil","Canada","Cape Verde","Chile","China","Colombia","Congo"],
        active: false
    }

    inputClick = () =>{
        console.log(document.activeElement.id);

        this.setState({active: true});
    }

    cancel = () =>{
        this.setState({active: false});
    }

    render(){
        const display = this.state.active ? 'block' : 'none';
        const zIndex = this.state.active ? '120' : '90';

        return (
            <Aux>
                <Backdrop active={this.state.active} click={this.cancel} />

                <div className="auto-complete-background">
                    <div className="divSearch" style={{zIndex : zIndex}}>
                    
                        <input type="text" 
                            placeholder="Pesquisar..."                         
                            onClick={this.inputClick} />

                        <i className="fa fa-search search-icon"></i>
                    </div>

                    <div className="div-autocomplete-list" style={{display : display, zIndex : zIndex}}>
                        <p>Context 1</p>
                        <p>Context 2</p>
                        <p>Context 3</p>
                    </div>
                </div>
 
            </Aux>
        );
    }
}

export default AutoComplete;