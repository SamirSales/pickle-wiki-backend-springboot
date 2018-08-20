import React from 'react';

import './UserItem.css';
import logo from '../../assets/img/profile.png';
import * as util from '../../utils';
import * as config from '../../config';

const userItem = ( props ) => {

    const userPermissions = util.userHasPermission(props.user, 'ADMIN') ? 'administrador' : 'editor';

    const hasImageProfile = props.user && props.user.pictureFileName && props.user.pictureFileName.trim() !== "";
    const imageSrc = hasImageProfile ? 
        config.URL_IMAGES_PROFILE + '/' + props.user.pictureFileName +"?t="+ new Date().getTime() :
        logo;

    return (
        <div className="userItem">            
            <div className="user-item-div-image">
                <img src={imageSrc} alt="Icon de usuário" height="47" width="47"/>
            </div>

            <div className="user-item-div-info">
                
                <div style={{width: '100%'}}>
                    <h3 className="user-item-name">{props.user.name}</h3>
                    <h3 style={{width : '66%', marginTop: '10px'}}>
                        login: <span className="user-item-span">{props.user.login}</span></h3>
                </div>

                <div style={{width: '100%'}}>
                    <h3 style={{width : '33%', marginTop: '3px'}}><span className="user-item-span">{userPermissions}</span></h3>
                    <h3 style={{width : '33%', marginTop: '3px'}}>e-mail: <span className="user-item-span">{props.user.email}</span></h3>

                    <div className="user-item-div-options">
                        <a className="fa fa-trash" onClick={props.removeAction} >{/**/}</a>
                        <a className="fa fa-edit" onClick={props.editAction} >{/**/}</a>                    
                    </div>
                </div>
            
            </div>            
        </div>       
    )
};

export default userItem;
