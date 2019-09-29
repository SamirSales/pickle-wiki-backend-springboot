package io.github.samirsales.dao;

import io.github.samirsales.model.entity.ImageEntity;
import io.github.samirsales.model.enums.ImageType;
import io.github.samirsales.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings("unused")
public class ImageDaoImpl implements ImageDao {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ImageEntity getById(Long id) {
        Optional<ImageEntity> optionalImageEntity = imageRepository.findById(id);
        return optionalImageEntity.orElse(null);
    }

    @Override
    public List<ImageEntity> getAll() {
        return (List<ImageEntity>) imageRepository.findByImageTypeOrderByName(ImageType.ARTICLE);
    }

    @Override
    public List<ImageEntity> searchByText(String search) {
        return (List<ImageEntity>) imageRepository.find(search);
    }

    @Override   
    public ImageEntity create(ImageEntity imageEntity) {
        return imageRepository.save(imageEntity);
    }

    @Override
    public void deleteById(long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public void update(ImageEntity imageEntity) {
        imageRepository.save(imageEntity);
    }
}
