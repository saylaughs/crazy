package com.saylaughs.crazysaylaugh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Say_Role implements Serializable {
    private Integer role_id;
    private String role_Name;
}
