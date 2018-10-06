package com.wangjiayu.springboot.mapper;

import com.wangjiayu.springboot.model.SUser;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SUser record);

    SUser selectByPrimaryKey(Integer id);

    List<SUser> selectAll();

    int updateByPrimaryKey(SUser record);
}