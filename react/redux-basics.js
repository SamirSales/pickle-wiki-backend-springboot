const redux = require('redux');
const createStore = redux.createStore;

const initialState = {
    user: null
}

// Reducer
const rootReducer = (state = initialState, action) =>{

    if(action.type === 'USER_LOGIN'){
        return {
            ...state,
            user: 'chico',
        };
    }

    if(action.type === 'USER_LOGOUT'){
        return {
            ...state,
            user: action.value
        }
    }

    return state;
};

// Store
const store = createStore(rootReducer);
console.log(store.getState());

// Subscription
store.subscribe(() => {
    console.log('[Subscription]', store.getState());
});

// Dispatching Action
store.dispatch({type: 'USER_LOGIN'});
store.dispatch({type: 'USER_LOGOUT', value: null});

