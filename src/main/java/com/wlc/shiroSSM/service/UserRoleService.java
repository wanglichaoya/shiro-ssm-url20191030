package com.wlc.shiroSSM.service;

import com.wlc.shiroSSM.pojo.Permission;
import com.wlc.shiroSSM.pojo.User;

/**
 * describe:
 *
 * @author 王立朝
 * @date 2019/10/31
 */
public interface UserRoleService {
    /**设置用户的角色**/
    void setRoles(User user , long[] roleIds);
    void deleteByUser(long userId);
    void deleteByRole(long roleId);
}
