package com.saylaughs.crazysaylaugh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.saylaughs.crazysaylaugh.Dao.UserMapper;
import com.saylaughs.crazysaylaugh.pojo.Say_User;
import com.saylaughs.crazysaylaugh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Say_User doLogin(String email, String password){
        Say_User sayUser=userMapper.login(email,password);
        if (sayUser==null){
            sayUser=null;
        }
        return sayUser;
    }

    public Boolean register(Say_User say_user) {
        say_user.setUserName(say_user.getEmail());
        if (say_user!=null){
            int isOk = userMapper.register(say_user);
            if(isOk>=1){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    @Override
    public PageInfo<Say_User> queryUserListPaged(Say_User say_user, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Say_User> say_users=userMapper.getAllUser();
        PageInfo<Say_User> pageInfo=new PageInfo<Say_User>(say_users);
        return pageInfo;
    }


}
