package com.wlc.shiroSSM.mapper;

import com.wlc.shiroSSM.pojo.RolePermission;
import com.wlc.shiroSSM.pojo.RolePermissionExample;
import java.util.List;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    List<RolePermission> selectByExample(RolePermissionExample example);

    RolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    int deleteRolePermitById(long id);
}