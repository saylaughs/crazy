package com.saylaughs.crazysaylaugh.control;

import com.saylaughs.crazysaylaugh.pojo.Say_Category;
import com.saylaughs.crazysaylaugh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloControl {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/test")
    public String test(Model model){

        return "/test";
    }
}
