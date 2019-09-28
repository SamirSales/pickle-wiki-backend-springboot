package io.github.samirsales.controller;

import io.github.samirsales.entity.Article;
import io.github.samirsales.entity.Dto.ArticleDTO;
import io.github.samirsales.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Article> getAllArticles(){
        return articleService.getAllArticles();
    }

    @RequestMapping(value = "/{url}", method = RequestMethod.GET)
    public ArticleDTO getArticleByUrl(@PathVariable("url") String url){
        return articleService.getArticleByUrl(url);
    }

    @RequestMapping(value = "/search/{search}", method = RequestMethod.GET)
    public Collection<Article>  getArticlesBySearch(@PathVariable("search") String search){
        return articleService.getArticlesBySearch(search);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeArticleById(@PathVariable("id") long id){
        articleService.removeArticleById(id);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateArticle(@RequestBody Article article){
        articleService.updateArticle(article);
    }

    @PreAuthorize("hasAnyRole('EDITOR')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertArticle(@RequestBody Article article){
        articleService.insertArticle(article);
    }
}
