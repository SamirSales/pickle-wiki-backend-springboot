import * as actionTypes from './actions';

const initialState = {
    appName: 'Pickle Wiki',
    user: {
        name: 'Francisco',
        login: 'chico',
        password: '123456'
    }
}

const reducer = (state = initialState, action) => {      

    switch(action.type){
        case actionTypes.USER_LOGIN:
            return{
                ...state,
                user: action.user
            };      
        case actionTypes.APP_NAME:
            return{
                ...state,
                appName: action.appName
            }
        default:
            break;
    }

    return state;
};

export default reducer;