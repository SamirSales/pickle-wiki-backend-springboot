package io.github.samirsales.dao;

import io.github.samirsales.entity.Article;
import io.github.samirsales.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Qualifier("postgres")
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Collection<Article> getAllArticles() {
        return articleRepository.findByActiveTrue();
    }

    @Override
    public Collection<Article> getTopVisitedArticles(int range) {
        return null;
    }

    @Override
    public Collection<Article> getArticlesBySearch(String search) {
        return articleRepository.find(search);
    }

    @Override
    public Article getArticleByUrl(String url) {
        return articleRepository.findByUrlAndActiveTrue(url);
    }

    @Override
    public void removeArticleById(long id) {
        Article article = articleRepository.findByIdAndActiveTrue(id);
        article.setActive(false);
        articleRepository.save(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void insertArticle(Article article) {
        articleRepository.save(article);
    }
}
