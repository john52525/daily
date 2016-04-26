package com.kidoo.daily.service.Iml;

import com.kidoo.daily.domain.sec.User;
import com.kidoo.daily.service.interf.SecService;
import org.mybatis.spring.SqlSessionTemplate;

public class SecServiceImpl implements SecService {

	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public int insertUser(User user) {
		return sqlSessionTemplate.insert("mapper.UserMapper.insertSelective", user);
	}

}
