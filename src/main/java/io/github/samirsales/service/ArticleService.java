package io.github.samirsales.service;

import io.github.samirsales.dao.ArticleDao;
import io.github.samirsales.dao.UserDao;
import io.github.samirsales.entity.Article;
import io.github.samirsales.entity.Dto.ArticleDTO;
import io.github.samirsales.entity.User;
import io.github.samirsales.exception.AuthorizationException;
import io.github.samirsales.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ArticleService {

    @Autowired
    @Qualifier("postgres")
    private ArticleDao articleDao;

    @Autowired
    @Qualifier("postgres")
    private UserDao userDao;

    public Collection<Article> getAllArticles(){
        return articleDao.getAllArticles();
    }

    public Collection<Article> getArticlesBySearch(String search){
        return articleDao.getArticlesBySearch(search);
    }

    protected Collection<Article> getTopVisitedArticles(int range){
        return articleDao.getTopVisitedArticles(range);
    }

    public ArticleDTO getArticleByUrl(String url){
        Article article = this.articleDao.getArticleByUrl(url);

        if(article != null){
            User user = this.userDao.getUserById(article.getLastEditorId());
            return new ArticleDTO(article, user);
        }
        return null;
    }

    public void removeArticleById(long id) {
        this.articleDao.removeArticleById(id);
    }

    public void updateArticle(Article article){

        UserSS userSS = UserService.authenticated();

        if(userSS == null) {
            throw new AuthorizationException("Access Denied");
        }
        article.setLastEditorId(userSS.getId());

        StringBuilder urlSB = new StringBuilder(buildURL(article.getTitle()) + "-" + buildURL(article.getContext()));

        int index = 1;

        Article searchedArticle = this.articleDao.getArticleByUrl(urlSB.toString());
        while (searchedArticle != null && !searchedArticle.getId().equals(article.getId())){
            index++;
            urlSB.append("-").append(index);
        }
        article.setUrl(urlSB.toString());
        article.setActive(true);


        this.articleDao.updateArticle(article);
    }

    public void insertArticle(Article article) {

        UserSS userSS = UserService.authenticated();

        if(userSS == null) {
            throw new AuthorizationException("Access Denied");
        }
        article.setLastEditorId(userSS.getId());

        StringBuilder urlSB = new StringBuilder(buildURL(article.getTitle()) + "-" + buildURL(article.getContext()));

        int index = 1;
        while (this.articleDao.getArticleByUrl(urlSB.toString()) != null){
            index++;
            urlSB.append("-").append(index);
        }
        article.setUrl(urlSB.toString());
        article.setActive(true);

        this.articleDao.insertArticle(article);
    }

    private String buildURL(String text){
        String[] arrayOfWords = text.toLowerCase().split(" ");
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<arrayOfWords.length; i++){

            if(i>0){
                stringBuilder.append("-");
            }
            stringBuilder.append(arrayOfWords[i]);
        }
        return stringBuilder.toString();
    }

}
