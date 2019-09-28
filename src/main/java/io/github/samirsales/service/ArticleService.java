package io.github.samirsales.service;

import io.github.samirsales.dao.ArticleDao;
import io.github.samirsales.dao.UserDao;
import io.github.samirsales.model.entity.Article;
import io.github.samirsales.model.dto.ArticleDTO;
import io.github.samirsales.model.entity.User;
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
    @SuppressWarnings("unused")
    private ArticleDao articleDao;

    @Autowired
    @Qualifier("postgres")
    @SuppressWarnings("unused")
    private UserDao userDao;

    public Collection<Article> getAllArticles(){
        return articleDao.getAll();
    }

    public Collection<Article> getArticlesBySearch(String search){
        return articleDao.searchByText(search);
    }

    public ArticleDTO getArticleByUrl(String url){
        Article article = this.articleDao.getByUrl(url);

        if(article != null){
            User user = this.userDao.getById(article.getLastEditorId());
            return new ArticleDTO(article, user);
        }
        return null;
    }

    public void removeArticleById(long id) {
        this.articleDao.deleteById(id);
    }

    public void updateArticle(Article article){

        UserSS userSS = UserService.authenticated();

        if(userSS == null) {
            throw new AuthorizationException("Access Denied");
        }
        article.setLastEditorId(userSS.getId());

        StringBuilder urlSB = new StringBuilder(buildURL(article.getTitle()) + "-" + buildURL(article.getContext()));

        int index = 1;

        Article searchedArticle = this.articleDao.getByUrl(urlSB.toString());
        while (searchedArticle != null && !searchedArticle.getId().equals(article.getId())){
            index++;
            urlSB.append("-").append(index);
        }
        article.setUrl(urlSB.toString());
        article.setActive(true);


        this.articleDao.update(article);
    }

    public void insertArticle(Article article) {

        UserSS userSS = UserService.authenticated();

        if(userSS == null) {
            throw new AuthorizationException("Access Denied");
        }
        article.setLastEditorId(userSS.getId());

        StringBuilder urlSB = new StringBuilder(buildURL(article.getTitle()) + "-" + buildURL(article.getContext()));

        int index = 1;
        while (this.articleDao.getByUrl(urlSB.toString()) != null){
            index++;
            urlSB.append("-").append(index);
        }
        article.setUrl(urlSB.toString());
        article.setActive(true);

        this.articleDao.insert(article);
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
