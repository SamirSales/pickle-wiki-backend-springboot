package io.github.samirsales.Service;

import io.github.samirsales.Dao.PictureDao;
import io.github.samirsales.Entity.Enum.PictureType;
import io.github.samirsales.Entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
public class PictureService {

    @Autowired
    @Qualifier("postgres")
    private PictureDao pictureDao;

    @Value("${uploading.image.path}")
    private String imagePath;

    public Picture getPictureById(Long id) {
        return pictureDao.getPictureById(id);
    }

    public List<Picture> getAll() {
        return pictureDao.getAll();
    }

    public List<Picture> getPicturesBySearch(String search) {
        return pictureDao.getPicturesBySearch(search);
    }

    public Picture insertPicture(MultipartFile file, String fileName, String pictureType) throws IOException {

        Picture picture = pictureDao.insertPicture(new Picture());
        picture.setName(fileName);
        picture.setPictureType(pictureType.equals("PROFILE") ? PictureType.PROFILE : PictureType.DEFAULT);
        picture.setFileExtension(getFileExtension(file.getOriginalFilename()));

        String path = imagePath+"/"+picture.get_id()+"."+picture.getFileExtension();
        File convertFile = new File(path);

        pictureDao.updatePicture(picture);
        System.out.println(path);

        try {
            convertFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(file.getBytes());
            fout.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            removePictureById(picture.get_id());
            throw ioException;
        }

        return picture;
    }

    public void removePictureById(long id) throws IOException {
        Picture picture = pictureDao.getPictureById(id);
        if(picture != null){
            File file = new File(imagePath+"/"+picture.getFileExtension());
            Files.deleteIfExists(file.toPath());
            pictureDao.removePictureById(id);
        }
    }

    public void updatePicture(Picture picture) {
        pictureDao.updatePicture(picture);
    }

    private String getFileExtension(String fullName) {
        if(fullName.isEmpty()){
            return "";
        }
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
