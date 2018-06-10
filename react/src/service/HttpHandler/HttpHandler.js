import axios from 'axios';

const url = "http://localhost:8080";

export const getUsers = function(){
    return axios.get(url + '/users/');
}

export const postUser = function(user){
    return axios.post(url + '/users/', user);
}

export const putUser = function(user){
    return axios.put(url + '/users/', user);
}