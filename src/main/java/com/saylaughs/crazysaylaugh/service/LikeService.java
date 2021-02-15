package com.saylaughs.crazysaylaugh.service;


import com.saylaughs.crazysaylaugh.pojo.Say_Like;

import java.util.List;

public interface LikeService {
    public  int giveLike(Say_Like say_like);

    public List<Say_Like> queryUserArt(String user_id);
}
