package com.saylaughs.crazysaylaugh.control;

import com.saylaughs.crazysaylaugh.conf.ServerConfig;
import com.saylaughs.crazysaylaugh.pojo.Say_Article;
import com.saylaughs.crazysaylaugh.pojo.Say_Category;
import com.saylaughs.crazysaylaugh.pojo.Say_Like;
import com.saylaughs.crazysaylaugh.pojo.Say_User;
import com.saylaughs.crazysaylaugh.service.ArticleService;
import com.saylaughs.crazysaylaugh.service.CategoryService;
import com.saylaughs.crazysaylaugh.service.LikeService;
import com.saylaughs.crazysaylaugh.util.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class indexControl {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleService articleService;

    @Autowired
    LikeService likeService;

    @Autowired
    ServerConfig serverConfig;

    @RequestMapping("/out")
    public String loginOut(HttpSession session){
        System.out.println("重定向");
        session.removeAttribute("user");
        return "redirect:/index";
    }

    @RequestMapping({"/index.html","/index","/"})
    public ModelAndView index(HttpSession session){
//        int i=1/0;
        ModelAndView model = new ModelAndView("/index");
        StringBuffer str=new StringBuffer();
        for (Say_Category s:categoryService.allCategory()) {
            str.append("<div><div class='menu-title'>"+s.getC_name()+"</div>");
            str.append("<div class='menus'><ul class='list-unstyled'>");
            for (Say_Category cate :s.getSay_categories()) {
                str.append("<li><a href='javascript:void(0)' value='"+serverConfig.getContextPath()+"/article/category/"+cate.getC_id()+"'>"+cate.getC_name()+"</a><span>"+cate.getC_count()+"</span></li>");
            }
            str.append("</ul></div>");
            str.append("</div>");
        }
        model.addObject("str",str.toString());
        List<Say_Article> say_articles = articleService.allArticle(new Say_Article(),1,6);
        model.addObject("article",say_articles);
        session= (Say_User)session.getAttribute("user")==null?null:session;
        List<Say_Like> say_likes = likeService.queryUserArt(UserSession.getUserId(session));
        List<String> list=new ArrayList<String>();
        for (Say_Like sayLike:say_likes) {
            list.add(sayLike.getA_id().toString());
        }
        model.addObject("like",list);
        return model;
    }

}
