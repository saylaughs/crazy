package com.saylaughs.crazysaylaugh.Dao;

import com.saylaughs.crazysaylaugh.pojo.Say_Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LikeMapper {
    public int giveLike(@Param("say_like") Say_Like say_like);

    public  int delLike(@Param("user_id") String user_id, @Param("a_id") String a_id);

    public int queryLike(@Param("user_id") String user_id, @Param("a_id") String a_id);

    public List<Say_Like> queryUserArt(@Param("user_id") String user_id);
}
