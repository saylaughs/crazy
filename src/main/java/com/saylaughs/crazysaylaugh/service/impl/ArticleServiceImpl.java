package com.saylaughs.crazysaylaugh.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.saylaughs.crazysaylaugh.Dao.ArticleMapper;
import com.saylaughs.crazysaylaugh.util.OftenMethod;
import com.saylaughs.crazysaylaugh.util.PageHelp;
import com.saylaughs.crazysaylaugh.pojo.Say_Article;
import com.saylaughs.crazysaylaugh.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public List<Say_Article> allArticle(Say_Article say_article, Integer page, Integer pageSize) {
         PageHelper.startPage(page, pageSize);
        List<Say_Article> say_articles = articleMapper.allArticle(say_article);
        PageInfo<Say_Article> pageHelper=new PageInfo<Say_Article>(say_articles);
        return pageHelper.getList();
    }

    @Override
    public PageHelp selectArticle(String queryText,Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Say_Article> say_articles = articleMapper.selectArticle(queryText);
        PageInfo<Say_Article> pageInfo=new PageInfo<Say_Article>(say_articles);
        PageHelp pageHelp=new PageHelp();
        pageHelp.setCurrPage(pageInfo.getPageNum());
        pageHelp.setEndPage(pageInfo.getNavigateLastPage());
        pageHelp.setPageSize(pageSize);
        pageHelp.setList(pageInfo.getList());
        return pageHelp;
    }

    @Override
    public PageHelp getArticleByCategory(Integer categoryId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Say_Article> articleByCategoryId = articleMapper.getArticleByCategoryId(categoryId);
        PageInfo<Say_Article> pageInfo=new PageInfo<>(articleByCategoryId);

        PageHelp pageHelp=new PageHelp();
        pageHelp.setCurrPage(pageInfo.getPageNum());
        pageHelp.setEndPage(pageInfo.getEndRow());
        pageHelp.setList(pageInfo.getList());
        return pageHelp;
    }

    @Override
    public int addArticle(Say_Article say_article) {
        int count=articleMapper.addArticle(say_article);
        return count;
    }

    @Override
    public List<Say_Article> getRecommendByCategory(Integer category_id) {
        List<Say_Article> recommendByCategory = articleMapper.getRecommendByCategory(category_id);
        List<Say_Article> list= OftenMethod.getRandom(recommendByCategory);
        return list;
    }

    @Override
    public List<Say_Article> getOrderByArticle() {
        List<Say_Article> orderByArticle = articleMapper.getOrderByArticle();
        return orderByArticle;
    }

    @Override
    public List<Say_Article> getTimeArticle() {
        return articleMapper.getTimeArticle();
    }

    @Override
    public PageHelp getOwnerArticle(Integer userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Say_Article> ownerArticle = articleMapper.getOwnerArticle(userId);
        PageInfo<Say_Article> pageInfo=new PageInfo<>(ownerArticle);
        PageHelp pageHelp=new PageHelp();
        pageHelp.setList(ownerArticle);
        pageHelp.setEndPage(pageInfo.getEndRow());
        pageHelp.setCurrPage(page);
        pageHelp.setPageSize(pageSize);
        return pageHelp;
    }


}
