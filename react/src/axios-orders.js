import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8090'
});

export default instance;

const url = "http://localhost:8090";

// eslint-disable-next-line
export const getUsers = function(token){
    return axios.get(url + '/users/',
    {headers: {'Authorization': token}});
}

export const insertUser = function(user, token){
    return axios.post(url + '/users/', user,
    {headers: {'Authorization': token}});
}

export const login = function(user){
    return axios.post(url + '/login', user);
}

export const getAuthUser = function(token){
    return axios.post(url + '/users/token', null,
    {headers: {'Authorization': token}});
}

export const updateUser = function(user, token){
    return axios.put(url + '/users/', user,
    {headers: {'Authorization': token}});
}

export const deleteUser = function(id, token){
    return axios.delete(url + '/users/'+id,
    {headers: {'Authorization': token}});
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

export const insertArticle = function(article, token){
    return axios.post(url + '/articles/', article,
    {headers: {'Authorization': token}} );
}

export const putArticle = function(article, token){
    return axios.put(url + '/articles/', article,
    {headers: {'Authorization': token}} );
}

export const deleteArticle = function(id, token){
    return axios.delete(url + '/articles/'+id,
    {headers: {'Authorization': token}} );
}

// pictures

export const getPictures = function(token){
    return axios.get(url + '/pictures/',
    {headers: {'Authorization': token}});
}

export const addPicture = function(token, file){
    return axios.post(url + '/pictures/upload', file,
    {headers: {'Authorization': token}});
}

export const deletePicture = function(token, fileId){
    return axios.delete(url + '/pictures/'+fileId,
    {headers: {'Authorization': token}});
}

export const updatePicture = function(token, picture){
    return axios.put(url + '/pictures/', picture,
    {headers: {'Authorization': token}});
}
