package com.saylaughs.crazysaylaugh.Dao;

import com.saylaughs.crazysaylaugh.pojo.Say_Comment;
import com.saylaughs.crazysaylaugh.pojo.Say_Reply;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    public List<Say_Comment> getAllForArticle(Integer aId);

    @Options(useGeneratedKeys = true,keyProperty = "c_id")
    @Insert(" INSERT INTO say_comment(`a_id`, `user_id`, `c_datetime`, `c_content`, `like`, `top`) VALUES (#{a_id},#{user_id},#{c_datetime},#{c_content},#{like},#{top});")
    public int addComment(Say_Comment say_comment);

    public int delByCId(Integer c_id);

    public int updateByCId(Integer c_id);

    public int addLike(@Param("c_id")String c_id,@Param("str")String str);
}
