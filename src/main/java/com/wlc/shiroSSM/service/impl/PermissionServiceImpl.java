package com.wlc.shiroSSM.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlc.shiroSSM.mapper.PermissionMapper;
import com.wlc.shiroSSM.pojo.Permission;
import com.wlc.shiroSSM.service.PermissionService;

@Service
public class PermissionServiceImpl  implements PermissionService{

	@Autowired PermissionMapper permissionMapper;

	@Override
	public Set<String> listPermissions(String userName) {
		List<Permission> permissions = permissionMapper.listPermissionsByUserName(userName);
		Set<String> result = new HashSet<>();
		for (Permission permission: permissions) {
            if(null != permission){
				result.add(permission.getName());
			}

		}
		return result;
	}
}
