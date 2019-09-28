package io.github.samirsales.repository;

import io.github.samirsales.entity.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    Article findByIdAndActiveTrue(Long id);

    Collection<Article> findByActiveTrue();

    Article findByUrlAndActiveTrue(String url);

    @Query(nativeQuery = true, value = "SELECT * FROM public.article "
            + "WHERE active = true AND (LOWER(unaccent(title)) LIKE '%' || LOWER(unaccent(:search)) || '%' "
            + "OR LOWER(unaccent(context)) LIKE '%' || LOWER(unaccent(:search)) || '%') ORDER BY title")
    Collection<Article> find(@Param("search") String search);
}
