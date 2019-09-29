package io.github.samirsales.repository;

import io.github.samirsales.model.enums.ImageType;
import io.github.samirsales.model.entity.ImageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface ImageRepository extends CrudRepository<ImageEntity, Long> {

    Optional<ImageEntity> findById(Long id);

    void deleteById(Long id);

    Collection<ImageEntity> findAll();

    Collection<ImageEntity> findByImageTypeOrderByName(ImageType imageType);

    @SuppressWarnings("unused")
    Collection<ImageEntity> findByNameContaining(String search);

    @Query(nativeQuery = true, value = "SELECT * FROM public.pictures "
            + "WHERE LOWER(unaccent(name)) LIKE '%' || LOWER(unaccent(:search)) || '%' ORDER BY name")
    Collection<ImageEntity> find(@Param("search") String search);
}
