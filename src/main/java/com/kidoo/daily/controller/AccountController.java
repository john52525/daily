package com.kidoo.daily.controller;

import com.kidoo.daily.domain.sec.User;
import com.kidoo.daily.service.interf.SecService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@RequestMapping(value = "/account")
@Controller
public class AccountController {

	HashMap<String, String> computter = new HashMap<String, String>();

	@Autowired
	SecService secService;

	@RequestMapping(value = "/regist.do", method = RequestMethod.POST)
	public String regist(HttpServletRequest httpServletRequest) {
		String name = httpServletRequest.getParameter("name");
		String username = httpServletRequest.getParameter("username");
		String password = httpServletRequest.getParameter("password");
		String confirm_password = httpServletRequest.getParameter("confirm_password");
		String agree = httpServletRequest.getParameter("agree");
		String email = httpServletRequest.getParameter("email");

		System.out.println("regist ===>>>\tname:" + name + ",username:" + username + ",password:" + password
				+ ",confirm_password:" + confirm_password + ",agree:" + agree + ",email:" + email);

		User user = new User();
		Date date = new Date();
		user.setCreatedTime(date);
		user.setPassword(password);
		user.setUpdateTime(date);
		user.setUserName(username);

		secService.insertUser(user);

		return "login";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest httpServletRequest, Model model) {
		String username = httpServletRequest.getParameter("username");
		String password = httpServletRequest.getParameter("password");
		System.out.println("username:" + username + "\t" + "password:" + password);
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			System.out.println(
					"token name:" + token.getUsername() + " and toeken password: " + new String(token.getPassword()));
			token.setRememberMe(true);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			return "redirect:/homepage.ctr";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			model.addAttribute("errormsg", e.getMessage());
			return "403";
		}
	}

	@RequestMapping(value = "getsign.do")
	@ResponseBody
	public String getSign(HttpServletRequest httpServletRequest) {
		httpServletRequest.getRemoteAddr();
		return null;
	}

	@RequestMapping(value = "/logout.do")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/homepage.ctr";
	}
}
