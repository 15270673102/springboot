package com.wangjiayu.springboot.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String state;

    private String email;
    private String phone;
    private String nickname;

    //用户的角色 一对多关系
    private List<Role> roleList;

}