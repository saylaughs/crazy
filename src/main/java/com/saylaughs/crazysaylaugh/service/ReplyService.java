package com.saylaughs.crazysaylaugh.service;

import com.saylaughs.crazysaylaugh.pojo.Say_Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyService {
    public List<Say_Reply> getReplyForComment(Integer id);

    public Say_Reply addReply(Say_Reply say_reply);

    public int addLike(String  r_id,String type);
}
