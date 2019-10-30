package com.wlc.shiroSSM.mapper;

import java.util.List;

import com.wlc.shiroSSM.pojo.Permission;

public interface PermissionMapper {
	public List<Permission> listPermissionsByUserName(String userName);
	
}
