package com.kidoo.daily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping(value = "/roles.ctr")
	public String test01() {
		System.out.println("enter roles");
		return "index";
	}

	@RequestMapping(value = "/perms.ctr")
	public String test02() {
		System.out.println("enter perms");
		return "index";
	}
}
