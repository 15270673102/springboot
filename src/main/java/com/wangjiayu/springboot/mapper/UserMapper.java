package com.wangjiayu.springboot.mapper;

import com.wangjiayu.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author home
 */
@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User findUserByName(String username);

    User getUserByName(String username);
}