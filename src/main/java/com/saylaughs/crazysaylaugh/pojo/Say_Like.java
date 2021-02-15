package com.saylaughs.crazysaylaugh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Say_Like implements Serializable {
    private  Integer id;
    private  Integer user_id;
    private  Integer a_id;
}
