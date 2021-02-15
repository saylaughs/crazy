package com.saylaughs.crazysaylaugh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Say_Category implements Serializable {
    private Integer c_id;
    private String  c_name;
    private Integer  c_count;
    private Integer c_parent;

    List<Say_Category> say_categories;
}
