package io.github.samirsales.dao;

import io.github.samirsales.model.entity.Picture;

import java.util.List;

public interface PictureDao {

    Picture getPictureById(Long id);

    List<Picture> getAll();

    List<Picture> getPicturesBySearch(String search);

    Picture insertPicture(Picture picture);

    void removePictureById(long id);

    void updatePicture(Picture picture);
}
