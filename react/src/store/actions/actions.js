export const USER_LOGIN = 'USER_LOGIN';
export const APP_NAME = 'APP_NAME';

export const AUTH_START = 'AUTH_START';
export const AUTH_SUCCESS = 'AUTH_SUCCESS';
export const AUTH_FAIL = 'AUTH_FAIL';


export const userLogin = (usr) =>{
    return{
        type: USER_LOGIN,
        user: usr
    };
};

export const appName = () =>{
    return{
        type: APP_NAME,
    };
};

