import axios from 'axios';

const backendURL = "http://localhost:8090";

// refresh token
export const refreshToken = function(token){
    return axios.post(backendURL + '/auth/refresh_token', token,
    {headers: {'Authorization': token}});
}

// users ------------------------------------------------
export const getUsers = function(token){
    return axios.get(backendURL + '/users/',
    {headers: {'Authorization': token}});
}

export const insertUser = function(user, token){
    return axios.post(backendURL + '/users/', user,
    {headers: {'Authorization': token}});
}

export const login = function(user){
    return axios.post(backendURL + '/login', user);
}

export const getAuthUser = function(token){
    return axios.post(backendURL + '/users/token', null,
    {headers: {'Authorization': token}});
}

export const updateUser = function(user, token){
    return axios.put(backendURL + '/users/', user,
    {headers: {'Authorization': token}});
}

export const deleteUser = function(id, token){
    return axios.delete(backendURL + '/users/'+id,
    {headers: {'Authorization': token}});
}

export const userSetting = function(user, token){
    return axios.put(backendURL + '/users/setting', user,
    {headers: {'Authorization': token}});
}

export const userUploadPicture = function(token, file){
    return axios.post(backendURL + '/users/update_picture', file,
    {headers: {'Authorization': token}});
}

export const userPasswordUpdate = function(token, fields){
    return axios.post(backendURL + '/users/update_password', fields,
    {headers: {'Authorization': token}});
}

// articles ------------------------------------------------

export const getArticles = function(){
    return axios.get(backendURL + '/articles/');
}

export const getArticleByUrl = function(url1){
    return axios.get(backendURL + '/articles/'+url1);
}

export const getArticleBySearch = function(search){
    return axios.get(backendURL + '/articles/search/'+search);
}

export const insertArticle = function(article, token){
    return axios.post(backendURL + '/articles/', article,
    {headers: {'Authorization': token}} );
}

export const putArticle = function(article, token){
    return axios.put(backendURL + '/articles/', article,
    {headers: {'Authorization': token}} );
}

export const deleteArticle = function(id, token){
    return axios.delete(backendURL + '/articles/'+id,
    {headers: {'Authorization': token}} );
}

// pictures ------------------------------------------------

export const getPictures = function(token){
    return axios.get(backendURL + '/pictures/',
    {headers: {'Authorization': token}});
}

export const addPicture = function(token, file){
    return axios.post(backendURL + '/pictures/upload', file,
    {headers: {'Authorization': token}});
}

export const deletePicture = function(token, fileId){
    return axios.delete(backendURL + '/pictures/'+fileId,
    {headers: {'Authorization': token}});
}

export const updatePicture = function(token, picture){
    return axios.put(backendURL + '/pictures/', picture,
    {headers: {'Authorization': token}});
}

export const getPicturesBySearch = function(token, search){
    return axios.get(backendURL + '/pictures/search/'+search,
    {headers: {'Authorization': token}});
}
