import React from 'react';

import './UserItem.css';
import logo from '../../assets/img/profile.png';

const userItem = ( props ) => {

    return (
        <div className="userItem">            
            <div className="user-item-div-image">
                <img src={logo} alt="Pickle Wiki" height="47" width="47"/>
            </div>

            <div className="user-item-div-info">
                <h3 className="user-item-name">{props.name}</h3>
                <h3 style={{width : '30%'}}>login: <span className="user-item-span">{props.login}</span></h3>
                <h3 style={{width : '30%'}}>e-mail: <span className="user-item-span">{props.email}</span></h3>

                <div className="user-item-div-options">
                
                    <a className="fa fa-trash">{/**/}</a>
                    <a className="fa fa-edit">{/**/}</a>                    
                </div>
            </div>
            
            
            
        </div>       
    )
};

export default userItem;
