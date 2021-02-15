package com.saylaughs.crazysaylaugh.control;

import com.saylaughs.crazysaylaugh.exception.ExceptionController;
import com.saylaughs.crazysaylaugh.pojo.Say_Comment;
import com.saylaughs.crazysaylaugh.pojo.Say_Reply;
import com.saylaughs.crazysaylaugh.service.CommentService;
import com.saylaughs.crazysaylaugh.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/reply")
@Controller
public class ReplyControl {

    @Autowired
    ReplyService replyService;

    @RequestMapping("/addReply")
    @ResponseBody
    public Map<String,String> addReply(Say_Reply say_reply){
        Map<String,String> hashMap=new HashMap<>();
        say_reply= replyService.addReply(say_reply);
        try {
            System.out.println(say_reply.getR_id()+"-------------");
            if (say_reply.getR_id()!=null){
                hashMap.put("message","1");
                hashMap.put("r_id",say_reply.getR_id()+"");
            }
            else{
                hashMap.put("message","0");
            }
        } catch (Exception e) {
            new ExceptionController();
            hashMap.put("message","-1");
        }
        return hashMap;
    }
}
