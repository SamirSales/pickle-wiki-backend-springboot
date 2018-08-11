package io.github.samirsales.Repository;

import io.github.samirsales.Entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    Collection<Article> findByActiveTrue();

    Collection<Article> findByTitleContainingOrContextContaining(String title, String context);

    Article findByUrl(String url);
}
