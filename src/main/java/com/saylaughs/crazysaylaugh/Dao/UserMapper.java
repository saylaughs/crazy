package com.saylaughs.crazysaylaugh.Dao;


import com.saylaughs.crazysaylaugh.pojo.Say_User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    public Say_User login(@Param("email") String email, @Param("password") String password);

    public int register(@Param("say_user") Say_User say_user);

    public List<Say_User> getAllUser();
}
