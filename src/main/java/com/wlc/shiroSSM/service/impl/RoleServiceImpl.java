package com.wlc.shiroSSM.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlc.shiroSSM.mapper.RoleMapper;
import com.wlc.shiroSSM.pojo.Role;
import com.wlc.shiroSSM.service.RoleService;

@Service
public class RoleServiceImpl  implements RoleService{

	@Autowired RoleMapper roleMapper;

	@Override
	public Set<String> listRoles(String userName) {
		List<Role> roles = roleMapper.listRolesByUserName(userName);
		Set<String> result = new HashSet<>();
		for (Role role: roles) {
			if(null != role){
				result.add(role.getName());
			}
		}
		return result;
	}
}
