const initialState = {
    user: {
        name: 'Francisco',
        login: 'chico',
        password: '123456'
    }
}

const reducer = (state = initialState, action) => {

    if(action.type === 'USER_LOGIN'){
        return{
            ...state,
            user: action.user
        };        
    }

    return state;
};

export default reducer;