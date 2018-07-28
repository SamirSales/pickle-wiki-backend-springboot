package io.github.samirsales.Dao;

import io.github.samirsales.Entity.Picture;

import java.util.List;

public interface PictureDao {

    Picture getPictureById(Long id);

    List<Picture> getAll();

    List<Picture> getPicturesByToken(String token);

    void insertPicture(Picture picture);

    void deletePicture(Picture picture);

    void editPicture(Picture picture);
}
