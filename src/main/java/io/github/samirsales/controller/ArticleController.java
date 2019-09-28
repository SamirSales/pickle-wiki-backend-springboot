package io.github.samirsales.controller;

import io.github.samirsales.model.entity.Article;
import io.github.samirsales.model.dto.ArticleDTO;
import io.github.samirsales.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/articles")
@SuppressWarnings("unused")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Article> getAll(){
        return articleService.getAllArticles();
    }

    @RequestMapping(value = "/{url}", method = RequestMethod.GET)
    public ArticleDTO getByUrl(@PathVariable("url") String url){
        return articleService.getArticleByUrl(url);
    }

    @RequestMapping(value = "/search/{search}", method = RequestMethod.GET)
    public Collection<Article>  getBySearch(@PathVariable("search") String search){
        return articleService.getArticlesBySearch(search);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") long id){
        articleService.removeArticleById(id);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Article article){
        articleService.updateArticle(article);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insert(@RequestBody Article article){
        articleService.insertArticle(article);
    }
}
