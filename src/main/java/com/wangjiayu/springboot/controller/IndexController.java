package com.wangjiayu.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wangjiayu.springboot.mapper.UsersMapper;
import com.wangjiayu.springboot.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangjiayu.springboot.model.SUser;
import com.wangjiayu.springboot.service.IUserservice;

@Controller
@ConfigurationProperties(prefix = "a")
public class IndexController {

    @Autowired
    private IUserservice userservice;
    @Autowired
    private UsersMapper usersMapper;

    @Value(value = "${user}")
    private String user;
    @Autowired
    private Environment env;

    private String key1;
    private String key2;
    private String key3;

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        request.setAttribute("key", "hello world");
        System.out.println(user);
        System.out.println(key1);
        System.out.println(key2);
        System.out.println(key3);
        System.out.println(env.getProperty("password"));
        return "index";
    }

    @GetMapping("/user")
    @ResponseBody
    public List<SUser> user() {
        List<SUser> list = userservice.selectAll();
        return list;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<Users> users() {
        List<Users> users = usersMapper.selectAll();
        return users;
    }
}
