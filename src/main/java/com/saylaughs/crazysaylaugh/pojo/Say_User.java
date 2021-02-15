package com.saylaughs.crazysaylaugh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
    public class Say_User implements Serializable {
    private Integer user_id;
    private String userName;
    private String password;
    private String  email;
    private String sex;
    private String personalized;
    private String portrait;
    private String region;
    private Integer state;
    private Integer role_id;
    private String permission;
    private Integer category_id;


    public void setSex(String sex) {
        if (Integer.parseInt(sex)==0){
            sex="男";
        }
        else{
            sex="女";
        }
        this.sex = sex;
    }
}
