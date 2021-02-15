package com.saylaughs.crazysaylaugh.Dao;

import com.saylaughs.crazysaylaugh.pojo.Say_Reply;
import org.apache.ibatis.annotations.*;

import java.security.Key;
import java.util.List;

@Mapper
public interface ReplyMapper {
    public List<Say_Reply> getReplyForComment(Integer id);

    @Options(useGeneratedKeys = true,keyProperty ="r_id")
    @Insert(" INSERT INTO `say_reply`(`c_id`, `user_id`, `reply_user_id`, `r_askTime`, `r_askContent`, `like`, `reply_user_name`) VALUES (#{c_id}, #{user_id}, #{reply_user_id},#{r_askTime},#{r_askContent}, #{like},#{reply_user_name});")
    public int addReply(Say_Reply say_reply);


    public int addLike(@Param("r_id")String r_id,@Param("str")String str);
}
