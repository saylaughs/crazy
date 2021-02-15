package com.saylaughs.crazysaylaugh.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Say_Reply implements Serializable {
    private Integer r_id;//回复id
    private Integer c_id;//评论id
    private Integer user_id;//回复
    private Integer reply_user_id;//被回复id
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date r_askTime;//
    private String  r_askContent;
    private Integer like;
    private Say_User say_user;
    private String reply_user_name;
}
