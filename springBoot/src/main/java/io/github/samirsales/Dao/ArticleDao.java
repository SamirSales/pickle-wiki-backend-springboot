package io.github.samirsales.Dao;

import io.github.samirsales.Entity.Article;

import java.util.Collection;

public interface ArticleDao {

    Collection<Article> getAllArticles();

    Collection<Article> getTopVisitedArticles(int range);

    Collection<Article> getAllArticlesBySearch(String search);

    Article getArticleByUrl(String url);

    void removeArticleById(long id);

    void updateArticle(Article article);

    void insertArticle(Article article);
}
