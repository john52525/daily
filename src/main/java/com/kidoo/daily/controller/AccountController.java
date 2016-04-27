package com.kidoo.daily.controller;

import com.kidoo.daily.domain.sec.User;
import com.kidoo.daily.service.RedisVisitorService;
import com.kidoo.daily.service.SecService;
import com.kidoo.daily.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequestMapping(value = "/account")
@Controller
public class AccountController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SecService secService;
    @Autowired
    RedisVisitorService redisVisitorService;

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public String register(HttpServletRequest httpServletRequest, Model model) {

        String ip = httpServletRequest.getRemoteAddr();
        Integer visitorRegisterTime = redisVisitorService.getVisitorRegisterTime(ip);
        if (visitorRegisterTime != null) {
            model.addAttribute("errormsg", "You can't register more than once in a day!");
            return "403";
        }
        String parameters[] = new String[6];
        String name = parameters[0] = httpServletRequest.getParameter("name");
        String username = parameters[1] = httpServletRequest.getParameter("username");
        String password = parameters[2] = httpServletRequest.getParameter("password");
        String confirm_password = parameters[3] = httpServletRequest.getParameter("confirm_password");
        String agree = parameters[4] = httpServletRequest.getParameter("agree");
        String email = parameters[5] = httpServletRequest.getParameter("email");
        boolean isEmpty = StringUtils.isEmptys(parameters);
        if (isEmpty) {
            model.addAttribute("errormsg", "You inputted some empty msg");
            return "403";
        }
        if (!password.equals(confirm_password)) {
            model.addAttribute("errormsg", "Your password doesn't match");
            return "403";
        }
        int usernameLength = username.length();
        int passwordLength = password.length();
        if (usernameLength < 6 && passwordLength < 6) {
            model.addAttribute("errormsg", "Your username or password is too short");
            return "403";
        }

        Integer user_id = secService.getUserIdByUserName(username);
        if (user_id != null) {
            model.addAttribute("errormsg", "Your username is duplicate");
            return "403";
        }


        User user = new User();
        Date date = new Date();
        user.setCreatedTime(date);
        user.setPassword(password);
        user.setUpdateTime(date);
        user.setUserName(username);

        secService.insertUser(user);
        boolean result = redisVisitorService.addVisitorRegisterTime(ip);

        System.out.println("regist ===>>>\tname:" + name + ",username:" + username + ",password:" + password
                + ",confirm_password:" + confirm_password + ",agree:" + agree + ",email:" + email + ",ip:" + ip + ",result:" + result);

        return "login";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(HttpServletRequest httpServletRequest, Model model) {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            return "redirect:/home.do";
        } catch (AuthenticationException e) {
            logger.error(e.getMessage());
            model.addAttribute("errormsg", e.getMessage());
            return "403";
        }
    }

    @RequestMapping(value = "/logout.do")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/home.do";
    }
}
