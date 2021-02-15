package com.saylaughs.crazysaylaugh.control;

import com.saylaughs.crazysaylaugh.pojo.Say_User;
import com.saylaughs.crazysaylaugh.service.CommentService;
import com.saylaughs.crazysaylaugh.util.OftenMethod;
import com.saylaughs.crazysaylaugh.util.PageHelp;
import com.saylaughs.crazysaylaugh.pojo.Say_Article;
import com.saylaughs.crazysaylaugh.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/article")
public class ArticleControl {

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/{a_id}")
    public String getArticle(@PathVariable String a_id, Model model){
        Say_Article say_article=new Say_Article();
        say_article.setA_id(a_id);
        List<Say_Article> say_articles = articleService.allArticle(say_article,1,1);
        Say_Article article;
        if (say_articles.size()!=0) {
            article = say_articles.get(0);
        } else {
            article=null;
            model.addAttribute("message","文章不见了。");
            return "/error/500";
        }
        model.addAttribute("art",article);
        model.addAttribute("comment",commentService.getAllForArticle(Integer.parseInt(article.getA_id())));
        return "/article";
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public  Map<String,String>  addArticle(Say_Article say_article){
        int i = articleService.addArticle(say_article);
        Map<String,String> map=new HashMap<>();
        if (i>=1){
            map.put("message","发表成功");
        }
        else if(i<=0){
            map.put("message","发表失败。");
        }
        else {
            map.put("message", "出现问题");
        }
        return  map;
    }


    @RequestMapping(value = "/hotArticle")
    @ResponseBody
    public PageHelp hotArticle(@RequestParam("text")String text,@RequestParam("page")String page,@RequestParam("pageSize")String pageSize){
//        List<Say_Article> say_articles = articleService.selectArticle(text, Integer.parseInt(page), Integer.parseInt(pageSize));
        PageHelp pageHelp = articleService.selectArticle(text, Integer.parseInt(page), Integer.parseInt(pageSize));
        return pageHelp;
    }


    @RequestMapping(value = "/test")
    @ResponseBody
    public PageHelp testArticle(){
        PageHelp pageHelp = articleService.selectArticle("欢迎", Integer.parseInt("1"), Integer.parseInt("5"));
        return pageHelp;
    }

    @ResponseBody
    @RequestMapping(value = "/category/{id}")
    public PageHelp getArticleById(@PathVariable("id")String id,/*@RequestParam("page")*/String page,/*@RequestParam("pageSize")*/String pageSize){
        PageHelp pageHelp = articleService.getArticleByCategory(Integer.parseInt(id),Integer.parseInt("1"), Integer.parseInt("5"));
        return pageHelp;
    }

    @ResponseBody
    @RequestMapping(value = "/recommend")
    public List<Say_Article> reCommendContr(HttpSession session){
        Say_User user = (Say_User) session.getAttribute("user");
        List<Say_Article> list;
        if (user!=null){
            list=articleService.getRecommendByCategory(user.getCategory_id());
        }
        else{
           list = OftenMethod.getRandom(articleService.allArticle(new Say_Article(), 1, 60));
        }
        return list;
    }


    @ResponseBody
    @RequestMapping(value = "/order")
    public List<Say_Article> orderBy(){
        List<Say_Article> list=articleService.getOrderByArticle();
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/time")
    public List<Say_Article> timeBy(){
        List<Say_Article> list=articleService.getTimeArticle();
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/ownerArticle")
    public PageHelp owner(HttpSession session){
        Say_User user = (Say_User) session.getAttribute("user");
        PageHelp pageHelp;
        if (user!=null) {
            pageHelp = articleService.getOwnerArticle(user.getUser_id(),1,5);
        } else {
            System.out.println("34344");
            pageHelp=new PageHelp();
            return pageHelp;
        }
        return pageHelp;
    }

}
