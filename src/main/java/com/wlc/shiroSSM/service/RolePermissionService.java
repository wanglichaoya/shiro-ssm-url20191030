package com.wlc.shiroSSM.service;

import com.wlc.shiroSSM.pojo.Role;

public interface RolePermissionService {

    void setPermissions(Role role, long[] permissionId);

    void deleteByRole(long roleId);

    void deleteByPermission(long permissionId);
}
