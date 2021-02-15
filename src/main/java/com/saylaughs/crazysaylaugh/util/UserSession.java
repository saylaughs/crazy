package com.saylaughs.crazysaylaugh.util;

import com.saylaughs.crazysaylaugh.pojo.Say_User;

import javax.servlet.http.HttpSession;

public class UserSession {

    public static String getUserName(HttpSession session)
    {
        if(session!=null) {
            Say_User user = (Say_User) session.getAttribute("user");
            return user.getUserName();
        }
        return "0";
    }


    //此处标注为Session  会话测试    返回为session user ID
    public static String getUserId(HttpSession session) {
        if (session != null) {
            Say_User user = (Say_User) session.getAttribute("user");
            return "1";
        }
        return "0";
    }
}
