package com.wlc.shiroSSM.mapper;

import com.wlc.shiroSSM.pojo.UserRole;
import com.wlc.shiroSSM.pojo.UserRoleExample;
import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    int deleteUserRoleByid(long id);
}