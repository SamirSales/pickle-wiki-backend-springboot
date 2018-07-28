package io.github.samirsales.Controller;

import io.github.samirsales.Entity.Picture;
import io.github.samirsales.Service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/pictures")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @Value("${uploading.image.path}")
    private String imagePath;

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/upload",method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file) throws Exception {

        Picture picture = pictureService.insertPicture(new Picture());
        picture.setFileName(picture.getId()+"."+pictureService.getFileExtension(file.getOriginalFilename()));

        String path = imagePath+"/"+picture.getFileName();
        File convertFile = new File(path);
        System.out.println(path);

        try {
            convertFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(file.getBytes());
            fout.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            pictureService.deletePicture(picture);
            throw ioException;
        }

        return new ResponseEntity<>("File is upload successfully", HttpStatus.OK);
    }
}
