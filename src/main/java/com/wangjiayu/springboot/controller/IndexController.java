package com.wangjiayu.springboot.controller;

import com.wangjiayu.springboot.shiro.RetryLimitHashedCredentialsMatcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher;

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, Model model, String captcha) {

        //校验验证码  session中的验证码
        String sessionCaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute(CaptchaController.KEY_CAPTCHA);
        if (null == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
            model.addAttribute("msg", "验证码错误！");
            return "login";
        }

        //添加用户认证信息
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();

        String msg = null;
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            return "index";//跳转到首页

        } catch (UnknownAccountException e) {
            msg = "账号不存在！";
        } catch (LockedAccountException e) {
            msg = "用户已经锁定,请联系管理员！";
        } catch (IncorrectCredentialsException e) {
            msg = "账号密码错误！";
        }
        model.addAttribute("msg", msg);
        return "login";//返回登录页面
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg", "安全退出！");
        return "login";
    }

    /**
     * 解除admin 用户的限制登录 写死的 方便测试
     * @return
     */
    @RequestMapping("/unlockAccount")
    public String unlockAccount(Model model) {
        model.addAttribute("msg", "用户解锁成功");
        retryLimitHashedCredentialsMatcher.unlockAccount("root");
        return "login";
    }

}
