package com.kidoo.daily.controller;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

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

    @RequestMapping(value = "/splitpage.do")
    public void test04() {
        List<Integer> result = sqlSessionTemplate.selectList("mapper.UserMapper.selectAllUserId", null, new RowBounds(0, 2));
        System.out.println(result.size());
        for (int i : result) {
            System.out.println(i);
        }
    }
}
