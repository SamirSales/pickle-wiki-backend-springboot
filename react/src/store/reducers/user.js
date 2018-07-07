import * as actionTypes from '../actions';

const initialState = {
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
        default:
            break;
    }

    return state;
};

export default reducer;