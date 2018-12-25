package com.wangjiayu.springboot.shiro;

import com.wangjiayu.springboot.mapper.UserMapper;
import com.wangjiayu.springboot.model.User;
import com.wangjiayu.springboot.redis.RedisUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 登陆次数限制
 */
@Slf4j
@Component
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    public static final String DEFAULT_RETRYLIMIT_CACHE_KEY_PREFIX = "shiro:cache:retrylimit:";
    @Setter
    private String keyPrefix = DEFAULT_RETRYLIMIT_CACHE_KEY_PREFIX;

    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    private String getRedisKickoutKey(String username) {
        return this.keyPrefix + username;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //获取用户名
        String username = (String) token.getPrincipal();
        //获取用户登录次数
        AtomicInteger retryCount = (AtomicInteger) redisUtil.get(getRedisKickoutKey(username));

        if (retryCount == null) {
            //如果用户没有登陆过,登陆次数加1 并放入缓存
            retryCount = new AtomicInteger(0);
        }
        if (retryCount.incrementAndGet() > 5) {
            //如果用户登陆失败次数大于5次 抛出锁定用户异常  并修改数据库字段
            User user = userMapper.getUserByName(username);
            if (user != null && "0".equals(user.getState())) {
                //数据库字段 默认为 0  就是正常状态 所以 要改为1
                //修改数据库的状态字段为锁定
                user.setState("1");
                userMapper.updateByPrimaryKey(user);
            }
            log.info("锁定用户" + user.getUsername());
            //抛出用户锁定异常
            throw new LockedAccountException();
        }

        //判断用户账号和密码是否正确
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //如果正确,从缓存中将用户登录计数 清除
            redisUtil.remove(getRedisKickoutKey(username));
        } else {
            redisUtil.set(getRedisKickoutKey(username), retryCount);
        }
        return matches;
    }

    /**
     * 根据用户名 解锁用户
     * @param username
     * @return
     */
    public void unlockAccount(String username) {
        User user = userMapper.getUserByName(username);
        if (user != null) {
            //修改数据库的状态字段为锁定
            user.setState("0");
            userMapper.updateByPrimaryKey(user);
            redisUtil.remove(getRedisKickoutKey(username));
        }
    }

}
