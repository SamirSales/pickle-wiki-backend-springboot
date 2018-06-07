package io.github.samirsales.Entity;

public class Picture {

    private Long id;
    private String url;
    private String name;
    private Long articleId;

    public Picture(Long id, String url, String name, Long articleId) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.articleId = articleId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
