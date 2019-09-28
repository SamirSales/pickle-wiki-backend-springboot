package io.github.samirsales.Service;

import io.github.samirsales.Dao.PictureDao;
import io.github.samirsales.Entity.Dto.PictureDTO;
import io.github.samirsales.Entity.Enum.PictureType;
import io.github.samirsales.Entity.Picture;
import io.github.samirsales.Util.ImageResizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureService {

    @Autowired
    @Qualifier("postgres")
    private PictureDao pictureDao;

    @Value("${uploading.image.path}")
    private String imagePath;

    private final String THUMBNAIL_FILE_INDICATOR = "_thumbnail";

    public PictureDTO getPictureById(Long id) {
        return new PictureDTO(pictureDao.getPictureById(id), THUMBNAIL_FILE_INDICATOR);
    }

    public List<PictureDTO> getAll() {
        Collection<Picture> pictures = pictureDao.getAll();
        return pictures.parallelStream().map((Picture picture) -> new PictureDTO(picture, THUMBNAIL_FILE_INDICATOR))
                .collect(Collectors.toList());
    }

    public List<PictureDTO> getPicturesBySearch(String search) {
        Collection<Picture> pictures = pictureDao.getPicturesBySearch(search);
        return pictures.parallelStream().map((Picture picture) -> new PictureDTO(picture, THUMBNAIL_FILE_INDICATOR))
                .collect(Collectors.toList());
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

            // build thumbnail
            ImageResizer imgResizer = new ImageResizer();
            imgResizer.resize(
                    imagePath+"/"+picture.get_id()+"."+picture.getFileExtension(),
                    imagePath+"/"+picture.get_id()+ THUMBNAIL_FILE_INDICATOR + "." + picture.getFileExtension(),
                    200);

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
            File file = new File(imagePath+"/"+picture.get_id()+"."+picture.getFileExtension());
            File fileThumbnail = new File(imagePath + "/" + picture.get_id()
                    + THUMBNAIL_FILE_INDICATOR + "." + picture.getFileExtension());
            Files.deleteIfExists(file.toPath());
            Files.deleteIfExists(fileThumbnail.toPath());
            pictureDao.removePictureById(id);
        }
    }

    public void updatePicture(Picture picture) {
        Picture pictureDB = pictureDao.getPictureById(picture.get_id());
        if(pictureDB != null){
            pictureDB.setName(picture.getName());
            pictureDao.updatePicture(pictureDB);
        }
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
