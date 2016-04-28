package com.kidoo.daily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping(value = "/authc.do")
	public void test00() {
		System.out.println("enter authc");
		//return "home";
	}

	@RequestMapping(value = "/roles.do")
	public void test01() {
		System.out.println("enter roles");
		//return "home";
	}

	@RequestMapping(value = "/perms.do")
	public void test02() {
		System.out.println("enter perms");
		//return "home";
	}

	@RequestMapping(value = "/user.do")
	public void test03() {
		System.out.println("enter user");
		//return "home";
	}
}
