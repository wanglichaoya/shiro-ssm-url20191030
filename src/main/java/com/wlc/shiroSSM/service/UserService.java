package com.wlc.shiroSSM.service;

import com.wlc.shiroSSM.pojo.User;

public interface UserService {
	public String getPassword(String name);

	User getUser(String userName);
}