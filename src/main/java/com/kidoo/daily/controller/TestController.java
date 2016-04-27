package com.kidoo.daily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping(value = "/authc.do")
	public String test00() {
		System.out.println("enter authc");
		return "home";
	}

	@RequestMapping(value = "/roles.do")
	public String test01() {
		System.out.println("enter roles");
		return "home";
	}

	@RequestMapping(value = "/perms.do")
	public String test02() {
		System.out.println("enter perms");
		return "home";
	}
}
