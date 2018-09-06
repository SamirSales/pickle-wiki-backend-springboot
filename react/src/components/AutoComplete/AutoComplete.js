import React, {PureComponent} from 'react';

import { withRouter } from 'react-router-dom';
import * as axios from '../../axios-orders';
import * as config from '../../config';

import Aux from '../../hoc/Aux/Aux';
import Backdrop from '../UI/Backdrop/Backdrop';
import './AutoComplete.css';

class AutoComplete extends PureComponent {

    state = {
        foundArticles : [],
        active: false,
        selectedItem: -1
    }

    inputClick = () =>{
        this.setState({active: true});
    }

    cancel = () =>{
        this.setState({active: false});
        document.getElementById("auto-complete-input").blur();
        document.getElementById("auto-complete-input").value = "";
    }

    search = (event) =>{
        if(event.target.value.trim().length >= 1){
            const search = encodeURIComponent(event.target.value.trim());

            axios.getArticleBySearch(search).then(res => {
                this.setState({foundArticles: res.data, selectedItem: -1});    
            }).catch(err => {
                console.log('error',err);
            });
        }else{
            this.setState({foundArticles: []}); 
        }
    }

    searchItemSelect(url){
        this.cancel();
        this.setState({
            foundArticles : [],
            active: false,
            selectedItem: -1
        })
        this.props.history.push({pathname: config.URL_HOME_PAGE + '/article/' + encodeURIComponent(url)});
    }

    onKeyUp = (event) =>{

        // ARROW DOWN KEY
        if(event.keyCode === 40){
            if(this.state.foundArticles.length > 0){
                if(this.state.selectedItem === -1){
                    this.setState({selectedItem: this.state.foundArticles[0].id});
                }else{
                    let i;
                    for (i = 0; i < this.state.foundArticles.length; i++) {
                        if(this.state.foundArticles[i].id === this.state.selectedItem &&
                            i+1 < this.state.foundArticles.length){
                                this.setState({selectedItem: this.state.foundArticles[i+1].id});
                                break;
                        }
                    }
                }
            }
        }

        // ARROW UP KEY
        if(event.keyCode === 38){
            if(this.state.foundArticles.length > 0){
                if(this.state.selectedItem !== -1){
                    let i;
                    for (i = this.state.foundArticles.length-1; i > 0; i--) {
                        if(this.state.foundArticles[i].id === this.state.selectedItem &&
                            i-1 < this.state.foundArticles.length){
                                this.setState({selectedItem: this.state.foundArticles[i-1].id});
                                break;
                        }
                    }
                }
            }
        }

        // ENTER KEY
        if(event.keyCode === 13){
            
            if(this.state.selectedItem !== -1){
                // console.log(this.state.foundArticles);
                for(let art of this.state.foundArticles){
                    if(art.id === this.state.selectedItem){
                        this.searchItemSelect(art.url);
                        break;
                    }
                }
            }
        }
    }

    render(){
        const display = this.state.active ? 'block' : 'none';
        const zIndex = this.state.active ? '120' : '90';

        let articles = null;

        if(this.state.foundArticles && this.state.foundArticles.length > 0){
            articles = this.state.foundArticles.map(art => {
                let itemDivClass = art.id === this.state.selectedItem ? ' auto-complete-selected-item' : '';
                itemDivClass = 'auto-complete-item'.concat(itemDivClass);
    
                return (
                    <div className={itemDivClass} key={art.id} onClick={this.searchItemSelect.bind(this, art.url)}>
                        <p className='auto-complete-item-title'>{art.title}</p>
                        <p className='auto-complete-item-context'><i>{art.context}</i></p>
                    </div>);
            });
        }
        
        return (
            <Aux>
                <Backdrop active={this.state.active} click={this.cancel} />

                <div className="auto-complete-background">
                    <div className="divSearch" style={{zIndex : zIndex}}>

                        <input type="text" id="auto-complete-input"
                            placeholder="Pesquisa Pickle Wiki"   
                            onKeyUp={this.onKeyUp}                      
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