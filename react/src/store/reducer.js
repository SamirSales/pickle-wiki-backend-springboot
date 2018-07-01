const initialState = {
    user: null
}

const reducer = (state = initialState, action) => {

    if(action.type === 'USER_LOGIN'){
        return{
            user: action.user
        };        
    }

    return state;
};

export default reducer;