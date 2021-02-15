package com.saylaughs.crazysaylaugh.control;

import com.saylaughs.crazysaylaugh.pojo.Say_User;
import com.saylaughs.crazysaylaugh.service.ArticleService;
import com.saylaughs.crazysaylaugh.service.UserService;
import com.saylaughs.crazysaylaugh.util.EmailSent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserControl {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;
//
//    @RequestMapping(value = "/index")
//    @ResponseBody
//    public String index(HttpSession session) {
//        Say_User user = (Say_User) session.getAttribute("user");
//        return userService.doLogin(user.getUserName(), user.getPassword()).toString();
//    }

    @RequestMapping(value = "/page")
    public String a() {
        return "login";
    }


    @RequestMapping("/login")
    @ResponseBody
    public Map<String,String>  doLogin(HttpSession session, @RequestParam(value = "email", required = true) String email, @RequestParam(value = "password", required = true) String password) {
        Say_User say_user = userService.doLogin(email, password);
        Map<String,String> map=new HashMap<>();
        if (say_user != null) {
            session.setAttribute("user", say_user);
            map.put("page","/user/index");
        }
        else{
            map.put("page","/user/index/error");
        }
        return  map;
    }

    int num = 0;

    @ResponseBody
    @RequestMapping("/email")
    public String doEmail(Say_User say_user) {
        System.out.println(say_user.toString()+"-----");
        Random random = new Random();
        num = random.nextInt(1000000);
        System.out.println(num);
        EmailSent.sentMail("3052068273@qq.com", say_user.getEmail(), num);
        return "{\"num\":" + num + "}";
    }


    @RequestMapping("/register")
    @ResponseBody
    public String doRegister(Say_User say_user, @RequestParam(value = "qqSent", required = true) String qqSent) {
        System.out.println(say_user.toString() + "---" + qqSent + "----");
        if (Integer.parseInt(qqSent) == num) {
            if (userService.register(say_user)) {
                return "{\"bool\":\"true\"}";
            } else {
                return "{\"bool\":\"false\"}";
            }
        }
        return "{\"bool\":\"false\"}";
    }
}
