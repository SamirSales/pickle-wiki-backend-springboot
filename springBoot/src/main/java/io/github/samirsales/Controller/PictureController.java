package io.github.samirsales.Controller;

import io.github.samirsales.Entity.Picture;
import io.github.samirsales.Service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

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
            pictureService.removePictureById(picture.getId());
            throw ioException;
        }

        return new ResponseEntity<>("File is upload successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Picture> getAllPictures(){
        return pictureService.getAll();
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Picture getPictureById(@PathVariable("id") long id){
        return pictureService.getPictureById(id);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removePictureById(@PathVariable("id") long id){
        pictureService.removePictureById(id);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePicture(@RequestBody Picture picture){
        pictureService.updatePicture(picture);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/search/{search}", method = RequestMethod.GET)
    public Collection<Picture>  getPicturesBySearch(@PathVariable("search") String search){
        return pictureService.getPicturesBySearch(search);
    }
}