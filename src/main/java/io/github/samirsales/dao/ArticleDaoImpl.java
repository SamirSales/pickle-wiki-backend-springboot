package io.github.samirsales.dao;

import io.github.samirsales.model.entity.Article;
import io.github.samirsales.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Qualifier("postgres")
@SuppressWarnings("unused")
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Collection<Article> getAll() {
        return articleRepository.findByActiveTrue();
    }

    @Override
    public Collection<Article> getArticlesBySearch(String search) {
        return articleRepository.find(search);
    }

    @Override
    public Article getByUrl(String url) {
        return articleRepository.findByUrlAndActiveTrue(url);
    }

    @Override
    public void deleteById(long id) {
        Article article = articleRepository.findByIdAndActiveTrue(id);
        article.setActive(false);
        articleRepository.save(article);
    }

    @Override
    public void update(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void insert(Article article) {
        articleRepository.save(article);
    }
}
