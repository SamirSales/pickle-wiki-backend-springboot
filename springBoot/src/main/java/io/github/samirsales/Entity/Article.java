package io.github.samirsales.Entity;

import java.util.List;

public class Article {

    private Long id;
    private String title;
    private String text;
    private int views;

    public Article(Long id, String title, String text, int views, List<Picture> pictures) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.views = views;
        this.pictures = pictures;
    }

    private List<Picture> pictures;

    public Article(Long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
