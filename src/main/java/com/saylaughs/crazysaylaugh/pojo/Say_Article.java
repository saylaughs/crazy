package com.saylaughs.crazysaylaugh.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Say_Article implements Serializable {
    private String a_id;
    private String user_id;
    private String title;
    private String content;
    private String body;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date send_time;
    private String favour;
    private Integer state;
    private Integer readSum;
    private String cover_photo;
    private String content_photo;
    private Integer category_id1;
    private Integer category_id2;
    private  Integer category_id3;
    private String a_text;
    private Say_User say_user;

}
