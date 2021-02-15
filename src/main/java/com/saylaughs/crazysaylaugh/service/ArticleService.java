package com.saylaughs.crazysaylaugh.service;

import com.saylaughs.crazysaylaugh.util.PageHelp;
import com.saylaughs.crazysaylaugh.pojo.Say_Article;

import java.util.List;

public interface ArticleService {

    public List<Say_Article> allArticle(Say_Article say_article, Integer page, Integer pageSize);

    public PageHelp selectArticle(String queryText, Integer page, Integer pageSize);

    public PageHelp getArticleByCategory(Integer categoryId, Integer page, Integer pageSize);

    public int addArticle(Say_Article say_article);

    public List<Say_Article> getRecommendByCategory(Integer category_id);

    public List<Say_Article> getOrderByArticle();

    public List<Say_Article> getTimeArticle();

    public PageHelp getOwnerArticle(Integer userId, Integer page, Integer pageSize);
}
