package com.wangjiayu.springboot.controller;

import com.wangjiayu.springboot.listener.ShiroSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private ShiroSessionListener shiroSessionListener;

    @RequestMapping("/user")
    public String name(Model model, HttpSession session) {
        String id = session.getId();

        model.addAttribute("user", "fdsa");
        return "user/user";
    }

    @RequestMapping(value = "/userCount", method = RequestMethod.GET)
    public String userCount(Model model) {
        model.addAttribute("userCount", shiroSessionListener.getSessionCount());
        return "user/userCount";
    }

}
