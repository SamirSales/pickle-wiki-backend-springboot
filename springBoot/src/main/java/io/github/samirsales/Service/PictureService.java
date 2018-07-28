package io.github.samirsales.Service;

import io.github.samirsales.Dao.PictureDao;
import io.github.samirsales.Entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class PictureService {

    @Autowired
    @Qualifier("fakeData")
    private PictureDao pictureDao;

    public Picture getPictureById(Long id) {
        return pictureDao.getPictureById(id);
    }

    public List<Picture> getAll() {
        return pictureDao.getAll();
    }

    public List<Picture> getPicturesByToken(String token) {
        return pictureDao.getPicturesByToken(token);
    }

    public void deletePicture(Picture picture) {
        pictureDao.deletePicture(picture);
    }

    public void editPicture(Picture picture) {
        pictureDao.editPicture(picture);
    }
}
