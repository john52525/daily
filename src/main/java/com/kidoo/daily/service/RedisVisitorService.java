package com.kidoo.daily.service;

public interface RedisVisitorService {

    boolean addVisitorRegisterTime(String ip);

    Integer getVisitorRegisterTime(String ip);
}
