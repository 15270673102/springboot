package com.wangjiayu.springboot.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {

    private Integer id;
    private String modelname;
    private String permission;
}