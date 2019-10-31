package com.wlc.shiroSSM.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlc.shiroSSM.mapper.PermissionMapper;
import com.wlc.shiroSSM.mapper.RolePermissionMapper;
import com.wlc.shiroSSM.pojo.Permission;
import com.wlc.shiroSSM.pojo.PermissionExample;
import com.wlc.shiroSSM.pojo.Role;
import com.wlc.shiroSSM.pojo.RolePermission;
import com.wlc.shiroSSM.pojo.RolePermissionExample;
import com.wlc.shiroSSM.service.PermissionService;
import com.wlc.shiroSSM.service.RoleService;
import com.wlc.shiroSSM.service.UserService;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    RolePermissionMapper rolePermissionMapper;

    /**
     * 根据用户名获取用户角色所对应的权限
     * **/
    private List<RolePermission> commonListRolePermission(String userName){
        List<Role> roles = roleService.listRoles(userName);
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (Role role : roles) {
            RolePermissionExample example = new RolePermissionExample();
            example.createCriteria().andRidEqualTo(role.getId());
            List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
            rolePermissions.addAll(rps);
        }
        return rolePermissions;
    }
    //根据用户名查询到所有的角色，然后根绝角色查询角色所对应的权限
    @Override
    public Set<String> listPermissions(String userName) {


        List<RolePermission> rolePermissions = commonListRolePermission(userName);
        Set<String> result = new HashSet<>();
        for (RolePermission rolePermission : rolePermissions) {
            Permission p = permissionMapper.selectByPrimaryKey(rolePermission.getPid());
            result.add(p.getName());
        }
        return result;
    }

    @Override
    public void add(Permission u) {
        permissionMapper.insert(u);
    }

    @Override
    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Permission u) {
        permissionMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    public Permission get(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }


    /**
     * 获取所有的权限
     **/
    @Override
    public List<Permission> list() {
        PermissionExample example = new PermissionExample();
        example.setOrderByClause("id desc");
        return permissionMapper.selectByExample(example);

    }

    /**
     * 根据角色id，获取角色拥有的权限。
     **/
    @Override
    public List<Permission> list(Role role) {
        List<Permission> result = new ArrayList<>();
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRidEqualTo(role.getId());
        List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
        for (RolePermission rolePermission : rps) {
            result.add(permissionMapper.selectByPrimaryKey(rolePermission.getPid()));
        }

        return result;
    }

    @Override
    public boolean needInterceptor(String requestURI) {
        //查询到所有权限
        List<Permission> list = list();
        for(Permission permission:list){
            if(permission.getUrl().equals(requestURI)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<String> listPermissionURLs(String userName) {

        List<RolePermission> rolePermissions = commonListRolePermission(userName);
        Set<String> result = new HashSet<>();
        for (RolePermission rolePermission : rolePermissions) {
            Permission p = permissionMapper.selectByPrimaryKey(rolePermission.getPid());
            result.add(p.getUrl());
        }


        return result;
    }

}