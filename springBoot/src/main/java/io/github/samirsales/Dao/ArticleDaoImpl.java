package io.github.samirsales.Dao;

import io.github.samirsales.Entity.Article;

import java.util.Collection;

public class ArticleDaoImpl implements ArticleDao {
    @Override
    public Collection<Article> getAllArticles() {
        return null;
    }

    @Override
    public Collection<Article> getTopVisitedArticles(int range) {
        return null;
    }

    @Override
    public Collection<Article> getArticlesBySearch(String search) {
        return null;
    }

    @Override
    public Article getArticleByUrl(String url) {
        return null;
    }

    @Override
    public void removeArticleById(long id) {

    }

    @Override
    public void updateArticle(Article article) {

    }

    @Override
    public void insertArticle(Article article) {

    }
}
