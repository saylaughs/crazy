package com.saylaughs.crazysaylaugh.service;

import com.saylaughs.crazysaylaugh.pojo.Say_Comment;
import com.saylaughs.crazysaylaugh.pojo.Say_Reply;

import java.util.List;

public interface CommentService {
    public List<Say_Comment> getAllForArticle(Integer aId);

    public Say_Comment addComment(Say_Comment say_comment);

    public int delByCId(Integer c_id);

    public int updateByCId(Integer c_id);

    public int addLike(String c_id,String type);

}
