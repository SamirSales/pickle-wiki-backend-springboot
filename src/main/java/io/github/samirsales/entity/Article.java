package io.github.samirsales.entity;

import io.github.samirsales.model.AuditModel;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
public class Article extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String body;

    @NaturalId(mutable = true)
    private String url;

    private String context;
    private Long lastEditorId;
    private boolean active;

    public Article(Long id, String title, String body, String url, String context, Long lastEditorId, boolean active) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.url = url;
        this.context = context;
        this.lastEditorId = lastEditorId;
        this.active = active;
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

    public Long getLastEditorId() {
        return lastEditorId;
    }

    public void setLastEditorId(long lastEditorId) {
        this.lastEditorId = lastEditorId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
