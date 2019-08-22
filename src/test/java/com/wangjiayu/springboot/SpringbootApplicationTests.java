package com.wangjiayu.springboot;

import com.wangjiayu.springboot.rabbitmq.Sender;
import com.wangjiayu.springboot.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootApplicationTests {

    @Autowired
    private Sender sender;

    @Test
    public void test4() {
        sender.send();
    }

}
