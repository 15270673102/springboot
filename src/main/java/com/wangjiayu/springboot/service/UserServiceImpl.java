package com.wangjiayu.springboot.service;

import com.wangjiayu.springboot.mapper.UserMapper;
import com.wangjiayu.springboot.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void insert(User user) throws Exception {
        userMapper.insert(user);
        System.out.println("保存数据了");
        throw new RuntimeException("save 抛异常了");
    }

}
