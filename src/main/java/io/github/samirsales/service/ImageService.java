package io.github.samirsales.service;

import io.github.samirsales.dao.ImageDao;
import io.github.samirsales.model.dto.ImageDTO;
import io.github.samirsales.model.entity.ImageEntity;
import io.github.samirsales.model.enums.ImageType;
import io.github.samirsales.util.ImageResizer;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ImageService {

    @Autowired
    @SuppressWarnings("unused")
    private ImageDao imageDao;

    @Value("${uploading.image.path}")
    @SuppressWarnings("unused")
    private String imagePath;

    private final String THUMBNAIL_FILE_INDICATOR = "_thumbnail";

    public ImageDTO getPictureById(Long id) {
        return new ImageDTO(imageDao.getById(id), THUMBNAIL_FILE_INDICATOR);
    }

    public List<ImageDTO> getAll() {
        Collection<ImageEntity> imageEntities = imageDao.getAll();
        return imageEntities.parallelStream().map((ImageEntity imageEntity) -> new ImageDTO(imageEntity, THUMBNAIL_FILE_INDICATOR))
                .collect(Collectors.toList());
    }

    public List<ImageDTO> getPicturesBySearch(String search) {
        Collection<ImageEntity> imageEntities = imageDao.searchByText(search);
        return imageEntities.parallelStream().map((ImageEntity imageEntity) -> new ImageDTO(imageEntity, THUMBNAIL_FILE_INDICATOR))
                .collect(Collectors.toList());
    }

    public ImageEntity insertPicture(MultipartFile file, String imageName, String pictureType) throws IOException {

        ImageEntity imageEntity = imageDao.create(new ImageEntity());
        ImageType imageType = pictureType.equals("PROFILE") ? ImageType.PROFILE : ImageType.ARTICLE;
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String path = imagePath+"/"+ imageEntity.getId()+"."+ imageEntity.getFileExtension();
        File convertFile = new File(path);

        ImageEntity updatedImageEntity = new ImageEntity(imageEntity.getId(), imageName, fileExtension, imageType);

        imageDao.update(updatedImageEntity);
        System.out.println(path);

        try {
            convertFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(file.getBytes());
            fout.close();

            // build thumbnail
            ImageResizer imgResizer = new ImageResizer();
            imgResizer.resize(
                    imagePath+"/"+ imageEntity.getId()+"."+ imageEntity.getFileExtension(),
                    imagePath+"/"+ imageEntity.getId()+ THUMBNAIL_FILE_INDICATOR + "." + imageEntity.getFileExtension(),
                    200);

        } catch (IOException ioException) {
            ioException.printStackTrace();
            removePictureById(imageEntity.getId());
            throw ioException;
        }

        return imageEntity;
    }

    public void removePictureById(long id) throws IOException {
        ImageEntity imageEntity = imageDao.getById(id);
        if(imageEntity != null){
            File file = new File(imagePath+"/"+ imageEntity.getId()+"."+ imageEntity.getFileExtension());
            File fileThumbnail = new File(imagePath + "/" + imageEntity.getId()
                    + THUMBNAIL_FILE_INDICATOR + "." + imageEntity.getFileExtension());
            Files.deleteIfExists(file.toPath());
            Files.deleteIfExists(fileThumbnail.toPath());
            imageDao.deleteById(id);
        }
    }

    public void updatePicture(ImageEntity imageEntity) {
        ImageEntity imageEntityDB = imageDao.getById(imageEntity.getId());
        if(imageEntityDB != null){
            ImageEntity updatedImageEntity = new ImageEntity(
                    imageEntityDB.getId(),
                    imageEntity.getName(),
                    imageEntityDB.getFileExtension(),
                    imageEntityDB.getImageType());
            imageDao.update(updatedImageEntity);
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
