package com.saylaughs.crazysaylaugh.service.impl;


import com.saylaughs.crazysaylaugh.Dao.ArticleMapper;
import com.saylaughs.crazysaylaugh.Dao.LikeMapper;
import com.saylaughs.crazysaylaugh.pojo.Say_Like;
import com.saylaughs.crazysaylaugh.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeMapper likeMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public int giveLike(Say_Like say_like) {
        if (likeMapper.queryLike(say_like.getUser_id().toString(),say_like.getA_id().toString())==1){
            int i = likeMapper.delLike(say_like.getUser_id().toString(), say_like.getA_id().toString());
            articleMapper.updLike();
            return i;
        }
        int i = likeMapper.giveLike(say_like);
        articleMapper.updLike();
       return  i;
    }

    @Override
    public List<Say_Like> queryUserArt(String user_id) {
        return likeMapper.queryUserArt(user_id);
    }
}
