package io.github.samirsales.Repository;

import io.github.samirsales.Entity.Enum.PictureType;
import io.github.samirsales.Entity.Picture;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PictureRepository extends CrudRepository<Picture, Long> {

    Picture findBy_id(Long id);

    void deleteBy_id(Long id);

    Collection<Picture> findAll();

    Collection<Picture> findByPictureType(PictureType pictureType);

    Collection<Picture> findByNameContaining(String search);
}
