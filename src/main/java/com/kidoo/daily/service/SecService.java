package com.kidoo.daily.service;

import com.kidoo.daily.domain.sec.User;

public interface SecService {

    int insertUser(User user);

    Integer getUserIdByUserName(String userName);

}
