package com.kidoo.daily.controller;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.util.JdbcUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    protected BasicDataSource dataSource;

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

    @RequestMapping(value = "/db.do")
    public String test05(Model model) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT password FROM sec_user WHERE user_name = ?");
            preparedStatement.setString(1, "000000");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String string = resultSet.getString(1);
                System.out.println("result is===>>>" + string);
                model.addAttribute("string", string);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
        } finally {
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeResultSet(resultSet);
        }
        return "test";
    }

}
