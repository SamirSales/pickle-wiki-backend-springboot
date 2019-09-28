package io.github.samirsales.Repository;

import io.github.samirsales.Entity.Enum.PictureType;
import io.github.samirsales.Entity.Picture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface PictureRepository extends CrudRepository<Picture, Long> {

    Picture findBy_id(Long id);

    void deleteBy_id(Long id);

    Collection<Picture> findAll();

    Collection<Picture> findByPictureTypeOrderByName(PictureType pictureType);

    Collection<Picture> findByNameContaining(String search);

    @Query(nativeQuery = true, value = "SELECT * FROM public.pictures "
            + "WHERE LOWER(unaccent(name)) LIKE '%' || LOWER(unaccent(:search)) || '%' ORDER BY name")
    Collection<Picture> find(@Param("search") String search);
}
