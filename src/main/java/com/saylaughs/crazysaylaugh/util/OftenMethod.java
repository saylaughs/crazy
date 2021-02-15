package com.saylaughs.crazysaylaugh.util;

import com.saylaughs.crazysaylaugh.pojo.Say_Article;

import java.util.ArrayList;
import java.util.List;

public class OftenMethod {

    public static List<Say_Article> getRandom(List<Say_Article> list){
        List<Say_Article> getList= null;
        if (list.size()!=0) {
            getList = new ArrayList<>();
        }
        for (int i=0;i<6;i++){
            int random = (int) (Math.random() * (list.size()));
            getList.add(list.get(random));
        }
        return getList;
    }
}
