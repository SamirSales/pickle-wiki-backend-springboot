package io.github.samirsales.Repository;

import io.github.samirsales.Entity.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    Article findByIdAndActiveTrue(Long id);

    Collection<Article> findByActiveTrue();

//    Collection<Article> findByTitleContainingIgnoreCaseOrContextContainingIgnoreCase(String title, String context);

    Article findByUrl(String url);

    @Query(nativeQuery = true, value = "SELECT * FROM public.article "
            + "WHERE LOWER(unaccent(title)) LIKE '%' || LOWER(unaccent(:title)) || '%' "
            + "OR LOWER(unaccent(context)) LIKE '%' || LOWER(unaccent(:context)) || '%' ORDER BY title")
    public Collection<Article> find(@Param("title") String title, @Param("context") String context);
}
