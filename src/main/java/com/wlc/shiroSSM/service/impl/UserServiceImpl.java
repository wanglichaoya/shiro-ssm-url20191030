package com.wlc.shiroSSM.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlc.shiroSSM.mapper.UserMapper;
import com.wlc.shiroSSM.pojo.User;
import com.wlc.shiroSSM.service.UserService;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired UserMapper userMapper;
	
	@Override
	public String getPassword(String name) {
		// TODO Auto-generated method stub
		User u  = userMapper.getByName(name);
		if(null==u)
			return null;
		return u.getPassword();
	}

	@Override
	public User getUser(String userName) {
		return userMapper.getUser(userName);
	}

}
