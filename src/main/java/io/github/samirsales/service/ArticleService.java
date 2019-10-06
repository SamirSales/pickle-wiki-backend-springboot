package io.github.samirsales.service;

import io.github.samirsales.dao.ArticleDao;
import io.github.samirsales.dao.UserDao;
import io.github.samirsales.exception.AuthorizationException;
import io.github.samirsales.model.dto.ArticleDTO;
import io.github.samirsales.model.entity.Article;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    @SuppressWarnings("unused")
    private ArticleDao articleDao;

    @Autowired
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
            Optional<UserEntity> userEntityOptional = this.userDao.getById(article.getLastEditorId());

            if(userEntityOptional.isPresent()){
                return new ArticleDTO(article, userEntityOptional.get());
            }
        }
        return null;
    }

    public void removeArticleById(long id) {
        this.articleDao.deleteById(id);
    }

    public void updateArticle(Article article){

        UserSecurity userSecurity = UserService.authenticated();

        if(userSecurity == null) {
            throw new AuthorizationException("Access Denied");
        }
        article.setLastEditorId(userSecurity.getId());

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

        UserSecurity userSecurity = UserService.authenticated();

        if(userSecurity == null) {
            throw new AuthorizationException("Access Denied");
        }
        article.setLastEditorId(userSecurity.getId());

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
