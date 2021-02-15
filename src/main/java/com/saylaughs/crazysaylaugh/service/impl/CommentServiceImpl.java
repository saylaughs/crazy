package com.saylaughs.crazysaylaugh.service.impl;

import com.saylaughs.crazysaylaugh.Dao.CommentMapper;
import com.saylaughs.crazysaylaugh.Dao.ReplyMapper;
import com.saylaughs.crazysaylaugh.pojo.Say_Comment;
import com.saylaughs.crazysaylaugh.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    ReplyMapper replyMapper;

    @Override
    public List<Say_Comment> getAllForArticle(Integer aId){
        List<Say_Comment> allForArticle = commentMapper.getAllForArticle(aId);
        for (Say_Comment say_comment:allForArticle){
            say_comment.setReplies(replyMapper.getReplyForComment(say_comment.getC_id()));
        }
        return allForArticle;
    }

    @Override
    public Say_Comment addComment(Say_Comment say_comment) {
        int addComment = commentMapper.addComment(say_comment);
        return say_comment;
    }

    @Override
    public int delByCId(Integer c_id) {
        int i = commentMapper.delByCId(c_id);
        return i;
    }

    @Override
    public int updateByCId(Integer c_id) {
        return commentMapper.updateByCId(c_id);
    }

    @Override
    public int addLike(String c_id,String type) {
         int i=commentMapper.addLike(c_id,type);
        return i;
    }


}
