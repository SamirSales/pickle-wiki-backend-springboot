package io.github.samirsales.Entity;

import java.util.List;

public class Article {

    private Long id;
    private String title;
    private String body;
    private String url;
    private String lastEditor;
    private int views;

    private Category category;

    private List<Picture> pictures;

    public Article(Long id, String title, String body, String url, int views, Category category, List<Picture> pictures) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.url = url;
        this.views = views;
        this.category = category;
        this.pictures = pictures;
        this.lastEditor = "";
    }

    public Article(){}

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(String lastEditor) {
        this.lastEditor = lastEditor;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
