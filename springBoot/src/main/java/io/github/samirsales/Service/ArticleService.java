package io.github.samirsales.Service;

import io.github.samirsales.Dao.ArticleDao;
import io.github.samirsales.Entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ArticleService {

    @Autowired
    @Qualifier("postgres")
    private ArticleDao articleDao;

    public Collection<Article> getAllArticles(){
        return articleDao.getAllArticles();
    }

    public Collection<Article> getArticlesBySearch(String search){
        return articleDao.getArticlesBySearch(search);
    }

    protected Collection<Article> getTopVisitedArticles(int range){
        return articleDao.getTopVisitedArticles(range);
    }

    public Article getArticleByUrl(String url){
        return this.articleDao.getArticleByUrl(url);
    }

    public void removeArticleById(long id) {
        this.articleDao.removeArticleById(id);
    }

    public void updateArticle(Article article){
        this.articleDao.updateArticle(article);
    }

    public void insertArticle(Article article) {
        this.articleDao.insertArticle(article);
    }
}
