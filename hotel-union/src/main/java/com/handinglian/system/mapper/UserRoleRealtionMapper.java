package com.handinglian.system.mapper;

import com.handinglian.system.entity.UserRoleRealtion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleRealtionMapper {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int insert(UserRoleRealtion record);

    int insertSelective(UserRoleRealtion record);
}