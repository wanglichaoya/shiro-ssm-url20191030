package com.wlc.shiroSSM.mapper;

import com.wlc.shiroSSM.pojo.User;

public interface UserMapper {

	public User getByName(String name);

    int createUser(User user);

    User getUser(String userName);
}
