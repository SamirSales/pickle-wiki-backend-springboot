package io.github.samirsales.Entity.Dto;

import io.github.samirsales.Entity.Picture;

public class PictureDTO {

    private Long _id;
    private String name;
    private String fileName;
    private String thumbFileName;
    private String pictureType;

    public PictureDTO(Picture picture){
        this._id = picture.get_id();
        this.name = picture.getName();
        this.pictureType = picture.getPictureType().getValue();
        this.fileName = picture.get_id() + "." + picture.getFileExtension();
        this.thumbFileName = fileName;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getThumbFileName() {
        return thumbFileName;
    }

    public void setThumbFileName(String thumbFileName) {
        this.thumbFileName = thumbFileName;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }
}
