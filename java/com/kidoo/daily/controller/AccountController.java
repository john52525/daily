package com.kidoo.daily.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/account")
@Controller
public class AccountController {

    @RequestMapping(value = "/regist.ctr", method = RequestMethod.POST)
    public String regist(HttpServletRequest httpServletRequest) {
        String name = httpServletRequest.getParameter("name");
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        String confirm_password = httpServletRequest.getParameter("confirm_password");
        String agree = httpServletRequest.getParameter("agree");
        String email = httpServletRequest.getParameter("email");

        System.out.println("name:" + name + "\n" + "username:" + username + "\n" +
                "password:" + password + "\n" + "confirm_password:" + confirm_password + "\n" +
                "agree:" + agree + "\n" + "email:" + email);
        return "login";
    }

    @RequestMapping(value = "/login.ctr", method = RequestMethod.POST)
    public String login(HttpServletRequest httpServletRequest, Model model) {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        System.out.println("username:" + username + "\n" + "password:" + password);
        try {
            //使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            System.out.println(token.getUsername() + "and password:" + new String(token.getPassword()));
            token.setRememberMe(true);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            return "redirect:/homepage.ctr";
        } catch (AuthenticationException e) {
            System.out.println("问题:" + e.getMessage() + "\n\n");
            e.printStackTrace();
            model.addAttribute("errormsg", e.getMessage());
            return "403";
        }
    }

    @RequestMapping(value = "/logout.ctr")
    public String logout(RedirectAttributes redirectAttributes) {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        return "redirect:/homepage.ctr";
    }
}
