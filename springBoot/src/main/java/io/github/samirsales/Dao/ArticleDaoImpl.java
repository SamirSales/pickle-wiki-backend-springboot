package io.github.samirsales.Dao;

import io.github.samirsales.Entity.Article;
import io.github.samirsales.Repository.ArticleRepository;
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
//        return articleRepository.findByTitleContainingIgnoreCaseOrContextContainingIgnoreCase(search, search);
        return articleRepository.find(search,search);
    }

    @Override
    public Article getArticleByUrl(String url) {
        return articleRepository.findByUrl(url);
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
