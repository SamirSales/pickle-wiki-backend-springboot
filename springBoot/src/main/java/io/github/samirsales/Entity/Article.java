package io.github.samirsales.Entity;

public class Article {

    private Long id;
    private String title;
    private String body;
    private String url;
    private String context;
    private User lastEditor;
    private int views;

    public Article(Long id, String title, String body, String url, String context, User lastEditor, int views) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.url = url;
        this.context = context;
        this.lastEditor = lastEditor;
        this.views = views;
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

    public User getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(User lastEditor) {
        this.lastEditor = lastEditor;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
