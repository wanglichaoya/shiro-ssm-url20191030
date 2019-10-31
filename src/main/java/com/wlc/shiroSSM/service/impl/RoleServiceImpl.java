package com.wlc.shiroSSM.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wlc.shiroSSM.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlc.shiroSSM.mapper.RoleMapper;
import com.wlc.shiroSSM.mapper.UserRoleMapper;
import com.wlc.shiroSSM.pojo.Role;
import com.wlc.shiroSSM.pojo.RoleExample;
import com.wlc.shiroSSM.pojo.User;
import com.wlc.shiroSSM.pojo.UserRole;
import com.wlc.shiroSSM.pojo.UserRoleExample;
import com.wlc.shiroSSM.service.RoleService;
import com.wlc.shiroSSM.service.UserService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    UserService userService;
    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    public Set<String> listRoleNames(String userName) {
        Set<String> result = new HashSet<>();
        List<Role> roles = listRoles(userName);
        for (Role role : roles) {
            result.add(role.getName());
        }
        return result;
    }

    @Override
    public List<Role> listRoles(String userName) {
        List<Role> roles = new ArrayList<>();
        User user = userService.getByName(userName);
        if (null == user) {
            return roles;
        } else {
            roles = listRoles(user);
        }
        return roles;
    }

    @Override
    public void add(Role u) {
        roleMapper.insert(u);
    }

    @Override
    public int delete(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Role u) {
        roleMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    public int deleteUserRoleByid(long id) {
        return userRoleMapper.deleteUserRoleByid(id);
    }

    @Override
    public int deleteRolePermitById(long id) {
        return rolePermissionMapper.deleteRolePermitById(id);
    }

    @Override
    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> list() {
        RoleExample example = new RoleExample();
        example.setOrderByClause("id desc");
        return roleMapper.selectByExample(example);

    }

    @Override
    public List<Role> listRoles(User user) {
        List<Role> roles = new ArrayList<>();

        UserRoleExample example = new UserRoleExample();

        example.createCriteria().andUidEqualTo(user.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);

        for (UserRole userRole : userRoles) {
            Role role = roleMapper.selectByPrimaryKey(userRole.getRid());
            roles.add(role);
        }
        return roles;
    }

}