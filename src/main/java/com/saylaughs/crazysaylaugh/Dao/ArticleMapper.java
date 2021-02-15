package com.saylaughs.crazysaylaugh.Dao;

import com.saylaughs.crazysaylaugh.pojo.Say_Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {

    public List<Say_Article> allArticle(@Param("say_article") Say_Article say_article);

    public List<Say_Article> selectArticle(@Param("queryText") String queryText);

    public int addArticle(@Param("say_article") Say_Article say_article);

    public int updLike();

    public List<Say_Article> getArticleByCategoryId(Integer id);

    public List<Say_Article> getRecommendByCategory(Integer category_id);

    public List<Say_Article> getOrderByArticle();

    public List<Say_Article> getTimeArticle();

    public List<Say_Article> getOwnerArticle(Integer userId);
}
