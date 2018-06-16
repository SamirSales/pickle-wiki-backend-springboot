import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8080'
});

export default instance;

const url = "http://localhost:8080";

// eslint-disable-next-line
export const getUsers = function(){
    return axios.get(url + '/users/');
}

export const postUser = function(user){
    return axios.post(url + '/users/', user);
}

export const putUser = function(user){
    return axios.put(url + '/users/', user);
}

export const deleteUser = function(id){
    return axios.delete(url + '/userss/'+id);
}