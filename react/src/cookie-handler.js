import Cookies from 'universal-cookie';

const COOKIE_TOKEN = 'pickle_wiki_token';

export const saveToken = function(token){
    const cookies = new Cookies();
    cookies.set(COOKIE_TOKEN, token, { path: '/' });
}

export const getToken = function(){
    const cookies = new Cookies();
    return cookies.get(COOKIE_TOKEN);
}