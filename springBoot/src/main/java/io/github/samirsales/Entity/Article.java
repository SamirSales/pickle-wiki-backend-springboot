package io.github.samirsales.Entity;

import java.util.List;

public class Article {

    private Long id;
    private String title;
    private String body;
    private int views;

    private List<Picture> pictures;

    public Article(Long id, String title, String body, int views, List<Picture> pictures) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.views = views;
        this.pictures = pictures;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
