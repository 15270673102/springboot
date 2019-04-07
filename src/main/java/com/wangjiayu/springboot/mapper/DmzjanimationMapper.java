package com.wangjiayu.springboot.mapper;

import com.wangjiayu.springboot.model.Dmzjanimation;
import java.util.List;

public interface DmzjanimationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dmzjanimation record);

    Dmzjanimation selectByPrimaryKey(Integer id);

    List<Dmzjanimation> selectAll();

    int updateByPrimaryKey(Dmzjanimation record);
}