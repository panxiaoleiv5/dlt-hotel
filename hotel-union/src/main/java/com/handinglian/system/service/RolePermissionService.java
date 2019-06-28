package com.handinglian.system.service;

import com.handinglian.system.entity.RolePermissionRelation;

import java.util.List;

public interface RolePermissionService {
    List<Integer> inquirePermissionIdList(Integer roleId);

    int createRolePermissionRelation(RolePermissionRelation rolePermissionRelation);

    int deleteRolePermission(Integer roleId, Integer permissionId);
}
