package com.kidoo.daily.controller;

import com.kidoo.daily.domain.sec.User;
import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/admin")
@Controller
public class AdminController {

    @Autowired
    protected BasicDataSource dataSource;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

//    @RequestMapping(value = "/showallaccount")
//    public String showAllAccount() {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = dataSource.getConnection();
//            preparedStatement = connection.prepareStatement("SELECT * FROM sec_user");
//
//            resultSet = preparedStatement.executeQuery();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.closeResultSet(resultSet);
//            JdbcUtils.closeStatement(preparedStatement);
//        }
//        return "account";
//    }

    @RequestMapping(value = "/showallaccount.do")
    public String showAllAccount() {
        List<User> list = sqlSessionTemplate.selectList("mapper.UserMapper.selectAll");
        for (User user : list) {
            System.out.println("---UserId:" + user.getUserId() + "---UserName:" + user.getUserName() + "---Password:" + user.getPassword());
        }
        return "account";
    }
}
