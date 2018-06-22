package io.github.samirsales.Controller;

import io.github.samirsales.Entity.Category;
import io.github.samirsales.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Category> getAllCategorys(){
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "/search/{search}", method = RequestMethod.GET)
    public Collection<Category>  getCategoriesBySearch(@PathVariable("search") String search){
        return categoryService.getCategoriesBySearch(search);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category getCategoryById(@PathVariable("id") long id){
        return categoryService.getCategoryById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeCategoryById(@PathVariable("id") long id){
        categoryService.removeCategoryById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCategory(@RequestBody Category category){
        categoryService.updateCategory(category);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertCategory(@RequestBody Category category){
        categoryService.insertCategory(category);
    }
}
