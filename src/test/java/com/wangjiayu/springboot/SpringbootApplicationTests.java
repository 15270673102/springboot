package com.wangjiayu.springboot;

import com.wangjiayu.springboot.model.User;
import com.wangjiayu.springboot.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

    @Resource
    private IUserService userService;

    @Test
    public void test4() throws Exception {
        User user = new User();
        user.setName("123");
        user.setAge(11);
        user.setEmail("123123@132");
        userService.insert(user);
    }

}
