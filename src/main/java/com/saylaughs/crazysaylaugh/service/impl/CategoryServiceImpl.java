package com.saylaughs.crazysaylaugh.service.impl;


import com.saylaughs.crazysaylaugh.Dao.CategoryMapper;
import com.saylaughs.crazysaylaugh.pojo.Say_Category;
import com.saylaughs.crazysaylaugh.service.CategoryService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    //cacheNames  cache组件  -->   key value

    @Cacheable(value = "category",key = "#root.methodName")
    public List<Say_Category> allCategory(){
        System.out.println("查询菜单缓存。。。。。。。。。。。。");
        List<Say_Category> say_categories = categoryMapper.allCategory();

        List<Say_Category> say=new ArrayList<Say_Category>();

        for (Say_Category s:say_categories) {
            int c_id=s.getC_id();
            List<Say_Category> list=new ArrayList<Say_Category>();
            for (Say_Category say_category:say_categories) {
                if (say_category.getC_parent()==c_id&&s.getC_parent()==0){
                    list.add(say_category);
                }
            }
            s.setSay_categories(list);
            if (s.getSay_categories().size()!=0){
                say.add(s);
            }
        }
        System.out.println("备入菜单缓存----------------------------");
        return say;
    }
}
