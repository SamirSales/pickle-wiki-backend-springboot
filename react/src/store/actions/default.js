import * as actionTypes from './actionTypes';

export const userLogin = (usr) =>{
    return{
        type: actionTypes.USER_LOGIN,
        user: usr
    };
};

export const appName = () =>{
    return{
        type: actionTypes.APP_NAME,
    };
};

export const token = (tkn) =>{
    return{
        type: actionTypes.TOKEN,
        token: tkn
    };
};