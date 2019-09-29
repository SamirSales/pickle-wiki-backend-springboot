package io.github.samirsales.dao;

import io.github.samirsales.model.entity.ImageEntity;

import java.util.List;

public interface ImageDao {

    ImageEntity getById(Long id);

    List<ImageEntity> getAll();

    List<ImageEntity> searchByText(String text);

    ImageEntity create(ImageEntity imageEntity);

    void deleteById(long id);

    void update(ImageEntity imageEntity);
}
