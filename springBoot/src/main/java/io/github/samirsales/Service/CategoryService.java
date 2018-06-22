package io.github.samirsales.Service;

import io.github.samirsales.Dao.CategoryDao;
import io.github.samirsales.Entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoryService {

    @Autowired
    @Qualifier("fakeData")
    private CategoryDao categoryDao;

    public Collection<Category> getAllCategories(){
        return categoryDao.getAllCategories();
    }

    public Collection<Category> getCategoriesBySearch(String search){
        return  categoryDao.getCategoriesBySearch(search);
    }

    public Category getCategoryById(long id){
        return this.categoryDao.getCategoryById(id);
    }

    public void removeCategoryById(long id) {
        this.categoryDao.removeCategoryById(id);
    }

    public void updateCategory(Category category){
        this.categoryDao.updateCategory(category);
    }

    public void insertCategory(Category category) {
        this.categoryDao.insertCategory(category);
    }
}
