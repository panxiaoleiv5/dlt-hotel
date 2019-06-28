package com.handinglian.system.mapper;
import java.util.List;

import com.handinglian.system.entity.RolePermissionRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RolePermissionRelationMapper {
    int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    int insert(RolePermissionRelation record);

    int insertSelective(RolePermissionRelation record);

    List<Integer> inquirePermissionIdList(@Param("roleId")Integer roleId);
}