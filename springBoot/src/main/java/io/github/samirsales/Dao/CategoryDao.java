package io.github.samirsales.Dao;

import io.github.samirsales.Entity.Category;

import java.util.Collection;

public interface CategoryDao {

    Collection<Category> getAllCategories();

    Collection<Category> getCategoriesBySearch(String search);

    Category getCategoryById(long id);

    void removeCategoryById(long id);

    void updateCategory(Category category);

    void insertCategory(Category category);
}
