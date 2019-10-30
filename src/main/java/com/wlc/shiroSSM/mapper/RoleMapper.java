package com.wlc.shiroSSM.mapper;

import java.util.List;

import com.wlc.shiroSSM.pojo.Role;

public interface RoleMapper {
	public List<Role> listRolesByUserName(String userName);
	
}
