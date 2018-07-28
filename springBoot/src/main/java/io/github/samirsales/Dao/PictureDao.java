package io.github.samirsales.Dao;

import io.github.samirsales.Entity.Picture;

import java.util.List;

public interface PictureDao {

    Picture getPictureById(Long id);

    List<Picture> getAll();

    List<Picture> getPicturesBySearch(String search);

    Picture insertPicture(Picture picture);

    void removePictureById(long id);

    void updatePicture(Picture picture);
}
