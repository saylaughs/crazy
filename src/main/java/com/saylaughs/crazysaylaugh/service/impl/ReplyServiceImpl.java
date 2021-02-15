package com.saylaughs.crazysaylaugh.service.impl;

import com.saylaughs.crazysaylaugh.Dao.ReplyMapper;
import com.saylaughs.crazysaylaugh.pojo.Say_Reply;
import com.saylaughs.crazysaylaugh.service.ReplyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReplyServiceImpl implements ReplyService {


    @Autowired
    ReplyMapper replyMapper;

    @Override
    public List<Say_Reply> getReplyForComment(Integer id){
        return replyMapper.getReplyForComment(id);
    }

    @Override
    public Say_Reply addReply(Say_Reply say_reply)   {
        replyMapper.addReply(say_reply);
        return say_reply;
    }

    @Override
    public int addLike(String r_id,String type) {
        int i = replyMapper.addLike(r_id,type);
        return i;
    }


}
