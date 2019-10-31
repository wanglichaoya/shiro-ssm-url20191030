package com.wlc.shiroSSM.service;

import com.wlc.shiroSSM.pojo.Permission;
import com.wlc.shiroSSM.pojo.Role;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    /**
     * shiro 授权的时候需要根据用户名称来获取用户所拥有的权限
     **/
    Set<String> listPermissions(String userName);

    /**
     * 查询所有权限
     **/
    List<Permission> list();

    /**
     * 新增权限
     **/
    void add(Permission permission);

    /**
     * 根据id删除权限
     **/
    void delete(Long id);

    /**
     * 根据id获取权限
     **/
    Permission get(Long id);

    /**
     * 更新权限
     **/
    void update(Permission permission);

    /**
     * 查看角色所拥有的权限列表
     **/
    List<Permission> list(Role role);
/**
 * 表示是否要进行拦截，判断依据是如果访问的某个url,
 * 在权限系统里存在，就要进行拦截。 如果不存在，就放行了。
 * **/
     boolean needInterceptor(String requestURI);

    /*listPermissionURLs(User user) 用来获取某个用户所拥有的权限地址集合*/
     Set<String> listPermissionURLs(String userName);
}