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

export const login = function(user){
    return axios.post(url + '/login', user);
}

export const putUser = function(user){
    return axios.put(url + '/users/', user);
}

export const deleteUser = function(id){
    return axios.delete(url + '/users/'+id);
}

// articles

export const getArticles = function(){
    return axios.get(url + '/articles/');
}

export const getArticleByUrl = function(url1){
    return axios.get(url + '/articles/'+url1);
}

export const getArticleBySearch = function(search){
    return axios.get(url + '/articles/search/'+search);
}

export const insertArticle = function(article){
    return axios.post(url + '/articles/', article);
}

export const putArticle = function(article){
    return axios.put(url + '/articles/', article);
}

export const deleteArticle = function(id){
    return axios.delete(url + '/articles/'+id);
}