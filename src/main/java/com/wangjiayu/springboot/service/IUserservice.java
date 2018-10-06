package com.wangjiayu.springboot.service;

import java.util.List;

import com.wangjiayu.springboot.model.SUser;

public interface IUserservice {

	List<SUser> selectAll();

}
