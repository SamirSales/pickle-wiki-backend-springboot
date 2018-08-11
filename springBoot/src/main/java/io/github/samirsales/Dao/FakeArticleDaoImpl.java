package io.github.samirsales.Dao;

import io.github.samirsales.Entity.Article;
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

        articles.add(new Article((long) 1, "Rato", "Sobre rato...","rato", "mamífero",null));

        articles.add(new Article((long) 2, "Git", "Sobre Git...", "git",
                "ferramenta de versionamento", null));

        articles.add(new Article((long) 3, "Java",
                "soiasjoixjsaojs\r## Sobre Java\r...\r" +
                "testando parágrago\r" +
                "> Testando *blá* e **blá blá**", "java", "linguagem de programação", null));
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
    public Collection<Article> getArticlesBySearch(String search) {
        ArrayList<Article> arraySearch = new ArrayList<>();

        search = search.toLowerCase();

        for(Article article : articles){
            if(article.getTitle().toLowerCase().contains(search) ||
                    article.getContext().toLowerCase().contains(search)){
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
