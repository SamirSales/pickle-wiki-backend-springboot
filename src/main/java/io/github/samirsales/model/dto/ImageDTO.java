package io.github.samirsales.model.dto;

import io.github.samirsales.model.entity.ImageEntity;

public class ImageDTO {

    private Long _id;
    private String name;
    private String fileName;
    private String thumbFileName;
    private String pictureType;

    public ImageDTO(ImageEntity imageEntity, String thumbnailFileIndicator){
        this._id = imageEntity.getId();
        this.name = imageEntity.getName();
        this.pictureType = imageEntity.getImageType().getValue();
        this.fileName = imageEntity.getId() + "." + imageEntity.getFileExtension();
        this.thumbFileName = imageEntity.getId() + thumbnailFileIndicator + "." + imageEntity.getFileExtension();
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
