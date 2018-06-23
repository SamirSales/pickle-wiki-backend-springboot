package io.github.samirsales.Entity;

import java.util.List;

public class Article {

    private Long id;
    private String title;
    private String body;
    private String url;
    private String context;
    private String lastEditor;
    private int views;

    private List<Picture> pictures;

    public Article(Long id, String title, String body, String url, String context, String lastEditor, int views,
                   List<Picture> pictures) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.url = url;
        this.context = context;
        this.lastEditor = lastEditor;
        this.views = views;
        this.pictures = pictures;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
