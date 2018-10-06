package com.wangjiayu.springboot;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wangjiayu.springboot.model.SUser;
import com.wangjiayu.springboot.redis.RedisUtil;
import com.wangjiayu.springboot.service.IUserservice;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	private IUserservice userservice;
	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void test7() throws Exception {
		redisUtil.set("username", "wangjiayu");

		System.err.println(redisUtil.get("username"));
	}

	@Test
	public void test1() throws Exception {
		List<SUser> list = userservice.selectAll();
		for (SUser sUser : list) {
			System.out.println(sUser);
		}
	}

}
