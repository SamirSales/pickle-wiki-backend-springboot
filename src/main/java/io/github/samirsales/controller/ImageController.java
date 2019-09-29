package io.github.samirsales.controller;

import io.github.samirsales.model.dto.ImageDTO;
import io.github.samirsales.model.entity.ImageEntity;
import io.github.samirsales.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/pictures")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/upload",method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("fileName") String fileName,
                                             @RequestParam("pictureType") String pictureType) throws Exception {
        imageService.insertPicture(file, fileName, pictureType);
        return new ResponseEntity<>("File is upload successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<ImageDTO> getAllPictures(){
        return imageService.getAll();
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ImageDTO getPictureById(@PathVariable("id") long id){
        return imageService.getPictureById(id);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removePictureById(@PathVariable("id") long id) throws IOException {
        imageService.removePictureById(id);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePicture(@RequestBody ImageEntity imageEntity){
        imageService.updatePicture(imageEntity);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/search/{search}", method = RequestMethod.GET)
    public Collection<ImageDTO>  getPicturesBySearch(@PathVariable("search") String search){
        return imageService.getPicturesBySearch(search);
    }
}
