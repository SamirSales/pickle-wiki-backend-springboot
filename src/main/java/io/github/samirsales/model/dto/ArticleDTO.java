package io.github.samirsales.model.dto;

import io.github.samirsales.model.entity.Article;
import io.github.samirsales.model.entity.User;

public class ArticleDTO {

    private Long id;
    private String title;
    private String context;
    private String body;
    private String url;
    private String updatedAt;

    private UserDTO lastEditor;

    private boolean active;

    public ArticleDTO(Article article, User user){
        id = article.getId();
        title = article.getTitle();
        context = article.getContext();
        body = article.getBody();
        url = article.getUrl();
        active = article.isActive();

        lastEditor = new UserDTO(user);
        lastEditor.setId(null);
        lastEditor.setLogin("");
        lastEditor.setId(null);
        lastEditor.setPermissions(null);

        updatedAt = article.getUpdatedAt().toString();
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public UserDTO getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(UserDTO lastEditor) {
        this.lastEditor = lastEditor;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
