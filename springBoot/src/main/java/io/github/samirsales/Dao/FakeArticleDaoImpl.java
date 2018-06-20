package io.github.samirsales.Dao;

import io.github.samirsales.Entity.Article;
import io.github.samirsales.Entity.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fakeData")
public class FakeArticleDaoImpl implements ArticleDao {

    private static ArrayList<Article> articles = new ArrayList<>();
    
    public FakeArticleDaoImpl(){
        Category animal = new Category(1L, "Animal");
        Category tool = new Category(2L, "Ferramenta de versionamento");
        Category language = new Category(3L, "linguagem de programação");

        articles.add(new Article((long) 1, "Rato", "Sobre rato...", "rato", 0,
                animal, new ArrayList<>()));

        articles.add(new Article((long) 2, "Git", "Sobre Git...", "git", 0,
                tool, new ArrayList<>()));

        articles.add(new Article((long) 3, "Java", "Sobre Java...", "java", 0,
                language, new ArrayList<>()));
    }
    
    private Map<String, Article> getArticleMap(){
        Map map = new HashMap();
        
        for(Article article : articles){
            map.put(article.getUrl(), article);
        }
        
        return map;
    }
    
    @Override
    public Collection<Article> getAllArticles() {
        return articles;
    }

    @Override
    public Collection<Article> getTopVisitedArticles(int range) {

        int length = range > articles.size() ? articles.size() : range;
        
        ArrayList<Article> articles = new ArrayList<>();
        for(int i = 0; i < length; i++){
            articles.add(articles.get(i));
        }
        
        return articles;
    }

    @Override
    public Collection<Article> getAllArticlesBySearch(String search) {
        ArrayList<Article> arraySearch = new ArrayList<>();

        search = search.toLowerCase();

        for(Article article : articles){
            if(article.getTitle().toLowerCase().contains(search) ||
                    article.getCategory().getName().toLowerCase().contains(search)){
                arraySearch.add(article);
            }
        }

        return arraySearch;
    }

    @Override
    public Article getArticleByUrl(String url) {
        return getArticleMap().get(url);
    }

    @Override
    public void removeArticleById(long id) {
        for(int i=0; i<articles.size(); i++){
            Article article = articles.get(i);
            if(article.getId() == id){
                articles.remove(i);
                break;
            }
        }
    }

    @Override
    public void updateArticle(Article article) {

        for(int i=0; i<articles.size(); i++){
            Article article1 = articles.get(i);
            if(article1.getId() == article.getId()){
                articles.remove(i);
                articles.add(article);
                break;
            }
        }
    }

    @Override
    public void insertArticle(Article article) {
        long newId = 0;

        for(Article article1 : articles){
            newId = article1.getId() > newId ? article1.getId() : newId;
        }
        
        article.setId(newId+1);
        articles.add(article);
    }
}
