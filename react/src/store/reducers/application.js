import * as actionTypes from '../actions/actionTypes';

const initialState = {
    appName: 'Pickle Wiki'
}

const reducer = (state = initialState, action) => {      

    switch(action.type){     
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