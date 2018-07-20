import React from 'react';
import ReactDOM from 'react-dom';

import { createStore, combineReducers, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
// import reducer from './store/reducer';
import { Provider } from 'react-redux';

import applicationReducer from './store/reducers/application';
import userReducer from './store/reducers/user';

import './index.css';
import '../node_modules/font-awesome/css/font-awesome.min.css';
import App from './containers/App';
import registerServiceWorker from './registerServiceWorker';


// import axios from 'axios';

// axios.defaults.baseURL = 'http://localhost:8080';
// axios.defaults.headers.common['Authorization'] = 'AUTH TOKEN';
// axios.defaults.headers.post['Content-Type'] = 'application/json';

// axios.interceptors.request.use(request => {
//     //Edit request config
//     return request;
// }, error =>{
//     return Promise.reject(error);
// })

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const logger = store =>{
    return next =>{
        return action =>{
            // console.log("[Middleware] Dispatching", action);
            const result = next(action);
            // console.log("[Middleware] next state", store.getState());
            return result;
        }
    }
}

const rootReducer = combineReducers({
    usr: userReducer,
    app: applicationReducer
})

const store = createStore(rootReducer, composeEnhancers(applyMiddleware(logger, thunk)));

ReactDOM.render(<Provider store={store} ><App /></Provider>, document.getElementById('root'));
registerServiceWorker();
