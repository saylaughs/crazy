package com.saylaughs.crazysaylaugh.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Say_Comment implements Serializable {
    private Integer c_id;  //评论id
    private Integer a_id;// 文章 id
    private Integer user_id;//用户id
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date c_datetime;//评论时间
    private String c_content;//评论内容
    private Integer like; //点赞人数
    private Integer top;
    private Integer isdel;
    private Say_User user;
    private List<Say_Reply> replies;
}
