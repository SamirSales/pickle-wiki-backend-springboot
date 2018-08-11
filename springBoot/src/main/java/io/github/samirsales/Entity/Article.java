package io.github.samirsales.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String url;

    private String context;
    private User lastEditor;

    public Article(Long id, String title, String body, String url, String context, User lastEditor) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.url = url;
        this.context = context;
        this.lastEditor = lastEditor;
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
