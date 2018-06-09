import axios from 'axios';

const url = "http://localhost:8080";

export const getUsers = function(){
    return axios.get(url + '/users/');
}