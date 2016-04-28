package com.kidoo.daily.mybatis.dialect;

public abstract class Dialect {

	public enum Type{
		MYSQL,
		ORACLE
	}
	
	public abstract String getLimitString(String sql, int skipResults, int maxResults);
	
}
