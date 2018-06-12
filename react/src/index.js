import React from 'react';
import ReactDOM from 'react-dom';
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

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
