package io.github.samirsales.model.entity;

import io.github.samirsales.model.enums.ImageType;

import javax.persistence.*;

@Entity(name = "images")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String fileExtension;

    @Enumerated
    private ImageType imageType;

    public ImageEntity(Long id, String name, String fileExtension, ImageType imageType) {
        this.id = id;
        this.name = name;
        this.fileExtension = fileExtension;
        this.imageType = imageType;
    }

    public ImageEntity(){
        this.name = "default name";
        this.fileExtension = ".png";
    }

    public  String getFilename(){
        return this.id + this.fileExtension;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public ImageType getImageType() {
        return imageType;
    }
}
