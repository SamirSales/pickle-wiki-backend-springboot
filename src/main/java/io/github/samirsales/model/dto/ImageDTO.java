package io.github.samirsales.model.dto;

import io.github.samirsales.model.entity.ImageEntity;


public class ImageDTO {

    private Long id;
    private String name;
    private String fileName;
    private String thumbFileName;
    private String pictureType;

    public ImageDTO(ImageEntity imageEntity, String thumbnailFileIndicator){
        this.id = imageEntity.getId();
        this.name = imageEntity.getName();
        this.pictureType = imageEntity.getImageType().getValue();
        this.fileName = imageEntity.getId() + "." + imageEntity.getFileExtension();
        this.thumbFileName = imageEntity.getId() + thumbnailFileIndicator + "." + imageEntity.getFileExtension();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("unused")
    public String getFileName() {
        return fileName;
    }

    @SuppressWarnings("unused")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @SuppressWarnings("unused")
    public String getThumbFileName() {
        return thumbFileName;
    }

    @SuppressWarnings("unused")
    public void setThumbFileName(String thumbFileName) {
        this.thumbFileName = thumbFileName;
    }

    @SuppressWarnings("unused")
    public String getPictureType() {
        return pictureType;
    }

    @SuppressWarnings("unused")
    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }
}
