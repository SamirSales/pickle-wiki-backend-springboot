package io.github.samirsales.Entity;

public class ArticleCategory {

    private Long id;
    private Long articleId;
    private Long categoryId;

    public ArticleCategory(Long id, Long articleId, Long categoryId) {
        this.id = id;
        this.articleId = articleId;
        this.categoryId = categoryId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
