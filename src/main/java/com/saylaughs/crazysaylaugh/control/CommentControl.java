package com.saylaughs.crazysaylaugh.control;

import com.saylaughs.crazysaylaugh.pojo.Say_Comment;
import com.saylaughs.crazysaylaugh.service.CommentService;
import com.saylaughs.crazysaylaugh.service.ReplyService;
import com.saylaughs.crazysaylaugh.service.impl.ReplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/comment")
@Controller
public class CommentControl {

    @Autowired
    CommentService commentService;

    @Autowired
    ReplyService replyService;

    @RequestMapping("/add")
    @ResponseBody
    public Map<String,String> add(Say_Comment say_comment){
        Map<String,String> hashMap=new HashMap<>();
        say_comment = commentService.addComment(say_comment);
        if (say_comment!=null){
            hashMap.put("message",say_comment.getC_id()+"");
            hashMap.put("user_id",say_comment.getUser_id()+"");
        }
        else{
            hashMap.put("message","评论失败");
        }
        return hashMap;
    }

    @RequestMapping("/del")
    @ResponseBody
    public Map<String,String> del(@RequestParam("c_id")String  c_id,@RequestParam("length")String  length){
        Map<String,String> hashMap=new HashMap<>();
        int len= Integer.parseInt(length);
        if (len>0){
            commentService.updateByCId(Integer.parseInt(c_id));
            hashMap.put("message","1");
        }
        else{
            commentService.delByCId(Integer.parseInt(c_id));
            hashMap.put("message","2");
        }
        return hashMap;
    }


    @RequestMapping("/addLike")
    @ResponseBody
    public Map<String,String> addLike(@RequestParam("type")String type,@RequestParam("math")String math,@RequestParam("id")String id){
        Map<String,String> hashMap=new HashMap<>();
        int i =0;
        if (type.equals("1")){
            i= commentService.addLike(id, math);
        }
        else if(type.equals("0")){
            i= replyService.addLike(id, math);
        }
        if (i>0){
            hashMap.put("message","1");
        }
        else{
            hashMap.put("message","0");
        }
        return hashMap;
    }
}
