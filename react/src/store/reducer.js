const initialState = {
    user: null
}

const reducer = (state = initialState, action) => {

    if(action.type === 'USER_LOGIN'){
        return{
            user: {
                name: 'Francisco',
                login: 'chico',
                password: '123456'
            }
        };        
    }

    return state;
};

export default reducer;