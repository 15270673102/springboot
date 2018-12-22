package com.wangjiayu.springboot.mapper;

import com.wangjiayu.springboot.model.User;

import java.util.List;

/**
 * @author home
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}