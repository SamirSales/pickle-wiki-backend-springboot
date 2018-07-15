import * as actionTypes from '../actions/actionTypes';

const initialState = {
    // user: {
    //     name: 'Francisco',
    //     login: 'chico',
    //     password: '123456',
    //     type: 'ADMIN'
    // }
    user: null,
    token: ""
}

const reducer = (state = initialState, action) => {      

    switch(action.type){
        case actionTypes.USER_LOGIN:
            return{
                ...state,
                user: action.user
            };  
        case actionTypes.TOKEN:
            return{
                ...state,
                token: action.token
            }
        default:
            break;
    }

    return state;
};

export default reducer;