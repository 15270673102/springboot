package com.wangjiayu.springboot.service;

import com.wangjiayu.springboot.model.User;

public interface IUserService {

    User findUserByName(String username);

    void update(User user);
}
