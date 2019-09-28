package io.github.samirsales.dao;

import io.github.samirsales.model.entity.Article;

import java.util.Collection;

public interface ArticleDao {

    Collection<Article> getAll();

    Collection<Article> searchByText(String text);

    Article getByUrl(String url);

    void deleteById(long id);

    void update(Article article);

    void insert(Article article);
}
