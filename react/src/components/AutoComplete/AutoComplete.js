import React, {PureComponent} from 'react';

import { withRouter } from 'react-router-dom';
import {getArticleBySearch} from '../../axios-orders';

import Aux from '../../hoc/Aux/Aux';
import Backdrop from '../UI/Backdrop/Backdrop';
import './AutoComplete.css';

class AutoComplete extends PureComponent {

    state = {
        foundArticles : [],
        active: false
    }

    inputClick = () =>{
        this.setState({active: true});
    }

    cancel = () =>{
        this.setState({active: false});
    }

    search = (event) =>{

        if(event.target.value.trim().length >= 1){
            getArticleBySearch(event.target.value.trim()).then(res => {
                this.setState({foundArticles: res.data});    
            }).catch(err => {
                console.log('error',err);
            });
        }else{
            this.setState({foundArticles: []}); 
        }
    }

    searchItemSelect(url){
        this.cancel();
        this.props.history.push({pathname: '/article/' + url});
    }

    render(){
        const display = this.state.active ? 'block' : 'none';
        const zIndex = this.state.active ? '120' : '90';

        const articles = this.state.foundArticles.map(art => {
            return (
                <div className='auto-complete-item' key={art.id} onClick={this.searchItemSelect.bind(this, art.url)}>
                    <p className='auto-complete-item-title'>{art.title}</p>
                    <p className='auto-complete-item-context'><i>{art.context}</i></p>
                </div>);
        });

        return (
            <Aux>
                <Backdrop active={this.state.active} click={this.cancel} />

                <div className="auto-complete-background">
                    <div className="divSearch" style={{zIndex : zIndex}}>

                        <input type="text" 
                            placeholder="Pesquisar..."                         
                            onClick={this.inputClick} onChange={this.search} />

                        <i className="fa fa-search search-icon"></i>
                    </div>

                    <div className="div-autocomplete-list" style={{display : display, zIndex : zIndex}}>
                        {articles}
                    </div>
                </div>
 
            </Aux>
        );
    }
}

export default withRouter(AutoComplete);