package io.github.samirsales.Dao;

import io.github.samirsales.Entity.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Qualifier("fakeData")
public class FakeCategoryDaoImpl implements CategoryDao {

    private static Map<Long, Category> categories;

    static {
        categories = new HashMap<Long, Category>(){
            {
                put((long) 1, new Category(1L, "Linguagem de programação"));
                put((long) 2, new Category(2L, "Animal"));
                put((long) 3, new Category(3L, "Ferramenta de versionamento"));
            }
        };
    }
    
    @Override
    public Collection<Category> getAllCategories() {
        return categories.values();
    }

    @Override
    public Collection<Category> getCategoriesBySearch(String search) {

        search = search.toLowerCase();

        ArrayList<Category> arrayList = new ArrayList<>();
        Collection<Category> categories = getAllCategories();

        for(Category category : categories){
            if(category.getName().toLowerCase().contains(search)){
                arrayList.add(category);
            }
        }

        return arrayList;
    }

    @Override
    public Category getCategoryById(long id) {
        return categories.get(id);
    }

    @Override
    public void removeCategoryById(long id) {
        categories.remove(id);
    }

    @Override
    public void updateCategory(Category category) {
        categories.put(category.getId(), category);
    }

    @Override
    public void insertCategory(Category category) {
        Collection<Category> categories = getAllCategories();
        long newId = 1;
        for(Category cat : categories){
            newId = newId < cat.getId() ? cat.getId() : newId;
        }

        category.setId(newId + 1);
        this.categories.put(newId, category);
    }
}
