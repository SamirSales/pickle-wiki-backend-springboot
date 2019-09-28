package io.github.samirsales.dao;

import io.github.samirsales.entity.Enum.PictureType;
import io.github.samirsales.entity.Picture;
import io.github.samirsales.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("postgres")
public class PictureDaoImpl implements PictureDao {

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public Picture getPictureById(Long id) {
        return pictureRepository.findBy_id(id);
    }

    @Override
    public List<Picture> getAll() {
        return (List<Picture>) pictureRepository.findByPictureTypeOrderByName(PictureType.DEFAULT);
    }

    @Override
    public List<Picture> getPicturesBySearch(String search) {
        return (List<Picture>) pictureRepository.find(search);
    }

    @Override   
    public Picture insertPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public void removePictureById(long id) {
        pictureRepository.deleteById(id);
    }

    @Override
    public void updatePicture(Picture picture) {
        pictureRepository.save(picture);
    }
}
