package com.saylaughs.crazysaylaugh.service;


import com.github.pagehelper.PageInfo;
import com.saylaughs.crazysaylaugh.pojo.Say_User;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface UserService {
    Say_User doLogin(String userName, String password);

    public Boolean register(Say_User say_user);

    public PageInfo<Say_User> queryUserListPaged(Say_User say_user, Integer page, Integer pageSize);
}
