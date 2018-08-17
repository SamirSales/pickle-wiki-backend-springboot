package io.github.samirsales.Entity;

import io.github.samirsales.Entity.Enum.PictureType;

import javax.persistence.*;

@Entity(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    private String name;
    private String fileExtension;

    @Enumerated(EnumType.STRING)
    private PictureType pictureType;

    public Picture(Long _id, String name, String fileExtension) {
        this._id = _id;
        this.name = name;
        this.fileExtension = fileExtension;
    }

    public Picture(){
        this.name = "default name";
        this.fileExtension = ".png"; //default
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public PictureType getPictureType() {
        return pictureType;
    }

    public void setPictureType(PictureType pictureType) {
        this.pictureType = pictureType;
    }
}
