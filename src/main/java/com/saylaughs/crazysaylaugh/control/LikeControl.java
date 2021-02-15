package com.saylaughs.crazysaylaugh.control;


import com.saylaughs.crazysaylaugh.pojo.Say_Like;
import com.saylaughs.crazysaylaugh.pojo.Say_User;
import com.saylaughs.crazysaylaugh.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LikeControl {

    @Autowired
    LikeService likeService;

    @RequestMapping("/like")
    @ResponseBody
    public Object updateLike(HttpSession session, @RequestParam("a_id") String a_id){
        Say_User user = (Say_User) session.getAttribute("user");
        Say_Like say_like=new Say_Like();
        say_like.setA_id(Integer.parseInt(a_id));
        say_like.setUser_id(user.getUser_id());
        int love=likeService.giveLike(say_like);
        if (love>=1){
            return "{\"info\":\"ok\"}";
        }
        else{
            return  "{\"info\":\"error\"}";
        }

    }
}
