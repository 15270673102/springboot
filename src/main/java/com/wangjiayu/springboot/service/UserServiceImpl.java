package com.wangjiayu.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangjiayu.springboot.mapper.SUserMapper;
import com.wangjiayu.springboot.model.SUser;

@Service
@Transactional
public class UserServiceImpl implements IUserservice {
	
	@Autowired
	private SUserMapper userMapper;

	@Override
	public List<SUser> selectAll() {
		 Page<SUser> page =PageHelper.startPage(1, 3);
		 List<SUser> list = userMapper.selectAll();
		 PageInfo<SUser> appsPageInfo = new PageInfo<>(list);
		  
		return appsPageInfo.getList();
	}

}
