package com.saylaughs.crazysaylaugh.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class pageControl {


    @RequestMapping("/article.html")
    public String article(){
        return "/article";
    }

    @RequestMapping("/owner.html")
    public String owner(){
        return "/owner";
    }

    @RequestMapping("/send.html")
    public String send(){
        return "/send";
    }

    @RequestMapping("/test.html")
    public String test(){
        return "/test";
    }

    @RequestMapping("/foot")
    public String foot(){
        return "/footer";
    }

    @RequestMapping("/public")
    public String common(){
        return "/public";
    }

    @RequestMapping("/error/500")
    public String error500(){
        return "/error/500";
    }


    @RequestMapping("/error/404")
    public String error404(){
        return "/error/404";
    }
}
