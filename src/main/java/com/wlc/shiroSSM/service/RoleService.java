package com.wlc.shiroSSM.service;

import com.wlc.shiroSSM.pojo.Role;
import com.wlc.shiroSSM.pojo.User;

import java.util.List;
import java.util.Set;

public interface RoleService {
    /**
     * shiro 授权的 时候需要根据用户名获取用户所拥有的角色，用 Set<String></String> 来接收
     **/
    Set<String> listRoleNames(String userName);

    /**
     * 根据用户名查询用户所拥有的角色列表
     **/
    List<Role> listRoles(String userName);

    List<Role> listRoles(User user);

    /**
     * 查询所有的角色
     **/
    List<Role> list();

    /**
     * 添加角色
     **/
    void add(Role role);

    /**
     * 根据id，修改用户角色的时候，回显用户的角色
     **/
    Role get(Long id);

    int delete(Long id);

    /**
     * 更新角色
     **/
    void update(Role role);

    /**根据角色id，删除用户角色表中的角色id**/
    int deleteUserRoleByid(long id);

    /**根据角色id，删除角色权限表中，角色对应的权限**/
    int deleteRolePermitById(long id);
}