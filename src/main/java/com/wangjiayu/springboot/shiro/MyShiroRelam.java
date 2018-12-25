package com.wangjiayu.springboot.shiro;

import com.wangjiayu.springboot.model.Permission;
import com.wangjiayu.springboot.model.Role;
import com.wangjiayu.springboot.model.User;
import com.wangjiayu.springboot.service.IUserService;
import com.wangjiayu.springboot.util.MyByteSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

@Slf4j
public class MyShiroRelam extends AuthorizingRealm {

    @Resource
    private IUserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("用户权限配置。。。。。。。。。。");

        //这里获取到的就是 上面方法放进去的username了
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //根据前端输入的用户名查询数据库中对应的记录
        User userInfo = userService.findUserByName(username);

        if (userInfo != null) {
            //访问@RequirePermission注解的url时触发
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            //获得用户的角色，及权限进行绑定
            for (Role role : userInfo.getRoleList()) {
                authorizationInfo.addRole(role.getRolename());
                for (Permission p : role.getPermissions()) {
                    authorizationInfo.addStringPermission(p.getPermission());
                }
            }
            return authorizationInfo;
        } else {
            return null;
        }
    }

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("验证用户登录信息....");

        //获取前端输入的用户名
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();

        //从数据库查询出User信息及用户关联的角色，权限信息，以备权限分配时使用
        User user = userService.findUserByName(username);
        if (user == null) {
            throw new UnknownAccountException("");//账号密码错误
        }
        if ("1".equals(user.getState())) {
            throw new LockedAccountException("");//用户已经锁定,请联系管理员！
        }

        //盐值
        ByteSource credentialsSalt = new MyByteSource(user.getUsername());

        //封装用户信息，构建AuthenticationInfo对象并返回
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
        return authenticationInfo;
    }

    public static void main(String[] args) {
        String hashAlgorithName = "MD5";
        String password = "111";
        int hashIterations = 1024;//加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes("wjy");
        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        System.out.println(obj);
    }
}
