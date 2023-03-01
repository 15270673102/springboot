package com.wangjiayu.springboot.controller;

import com.wangjiayu.springboot.mapper.UserMapper;
import com.wangjiayu.springboot.model.User;
import com.wangjiayu.springboot.service.IAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author 迅捷羽翼
 */

@RestController
@Slf4j
public class IndexController {

    @Resource
    private IAsyncService asyncService;
    //@Resource
    //private Sender sender;
    @Resource
    private UserMapper userMapper;

    @GetMapping(value = "/test2")
    public void test2() throws InterruptedException, ExecutionException {

        Future<String> futer1 = asyncService.test1();
        Future<String> futer2 = asyncService.test1();
        Future<String> futer3 = asyncService.test1();
        System.out.println(futer1.get());
        System.out.println(futer2.get());
        System.out.println(futer3.get());
    }


    //@GetMapping(value = "/test1")
    //public void test1() {
    //    sender.send();
    //}

    @GetMapping(value = "/test")
    public List<User> test() {
        return userMapper.selectAll();
    }

}
