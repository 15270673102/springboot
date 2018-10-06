package com.wangjiayu.springboot.mapper;

import com.wangjiayu.springboot.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    Users selectByPrimaryKey(Integer id);

    List<Users> selectAll();

    int updateByPrimaryKey(Users record);
}