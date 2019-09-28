package io.github.samirsales.dao;

import io.github.samirsales.model.entity.Article;

import java.util.Collection;

public interface ArticleDao {

    Collection<Article> getAllArticles();

    Collection<Article> getTopVisitedArticles(int range);

    Collection<Article> getArticlesBySearch(String search);

    Article getArticleByUrl(String url);

    void removeArticleById(long id);

    void updateArticle(Article article);

    void insertArticle(Article article);
}
