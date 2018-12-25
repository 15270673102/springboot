package com.wangjiayu.springboot.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Role implements Serializable {

    private Integer id;
    private String rolename;
    private String roledesc;
    private List<Permission> permissions;//角色权限关系  多对多  一个角色对应多个权限
}