package io.github.samirsales.model.dto;

import io.github.samirsales.model.dto.user.UserDTO;
import io.github.samirsales.model.entity.Article;
import io.github.samirsales.model.entity.UserEntity;

public class ArticleDTO {

    private Long id;
    private String title;
    private String context;
    private String body;
    private String url;
    private String updatedAt;

    private UserDTO lastEditor;

    private boolean active;

    public ArticleDTO(Article article, UserEntity userEntity){
        id = article.getId();
        title = article.getTitle();
        context = article.getContext();
        body = article.getBody();
        url = article.getUrl();
        active = article.isActive();

        lastEditor = new UserDTO(userEntity);

        updatedAt = article.getUpdatedAt().toString();
    }

    public Long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public String getTitle() {
        return title;
    }

    @SuppressWarnings("unused")
    public String getContext() {
        return context;
    }

    @SuppressWarnings("unused")
    public String getBody() {
        return body;
    }

    @SuppressWarnings("unused")
    public String getUrl() {
        return url;
    }

    @SuppressWarnings("unused")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @SuppressWarnings("unused")
    public UserDTO getLastEditor() {
        return lastEditor;
    }

    @SuppressWarnings("unused")
    public boolean isActive() {
        return active;
    }
}
