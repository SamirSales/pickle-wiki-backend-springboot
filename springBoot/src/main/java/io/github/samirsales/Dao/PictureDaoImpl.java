package io.github.samirsales.Dao;

import io.github.samirsales.Entity.Enum.PictureType;
import io.github.samirsales.Entity.Picture;
import io.github.samirsales.Repository.PictureRepository;
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
        return (List<Picture>) pictureRepository.findByPictureType(PictureType.DEFAULT);
    }

    @Override
    public List<Picture> getPicturesBySearch(String search) {
        return (List<Picture>) pictureRepository.findByNameContaining(search);
    }

    @Override
    public Picture insertPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public void removePictureById(long id) {
        pictureRepository.deleteBy_id(id);
    }

    @Override
    public void updatePicture(Picture picture) {
        pictureRepository.save(picture);
    }
}
