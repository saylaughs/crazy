package com.saylaughs.crazysaylaugh.control;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.saylaughs.crazysaylaugh.pojo.*;
import com.saylaughs.crazysaylaugh.service.ArticleService;
import com.saylaughs.crazysaylaugh.service.CategoryService;
import com.saylaughs.crazysaylaugh.service.LikeService;
import com.saylaughs.crazysaylaugh.util.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TestControl {

    @RequestMapping("/aaa")
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public String getTest(Model model){
        Test test=new Test(1,"222",new Date());
        model.addAttribute("t",test);
        return "/test";
    }

}
