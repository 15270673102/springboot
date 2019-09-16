package com.wangjiayu.springboot;

import com.wangjiayu.springboot.model.User;
import com.wangjiayu.springboot.properties.TestProperties;
import com.wangjiayu.springboot.rabbitmq.Sender;
import com.wangjiayu.springboot.redis.RedisUtil;
import com.wangjiayu.springboot.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceRefs;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootApplicationTests {

    @Autowired
    private Sender sender;
    @Resource
    private IUserService userService;
    @Resource
    private TestProperties testProperties;

    @Test
    public void test4() {
        System.out.println(testProperties);
    }

}
