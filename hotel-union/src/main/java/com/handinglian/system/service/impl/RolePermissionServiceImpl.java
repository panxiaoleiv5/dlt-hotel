package com.handinglian.system.service.impl;

import com.handinglian.system.entity.RolePermissionRelation;
import com.handinglian.system.mapper.RolePermissionRelationMapper;
import com.handinglian.system.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionRelationMapper rolePermissionRelationMapper;

    @Override
    public List<Integer> inquirePermissionIdList(Integer roleId) {
        return rolePermissionRelationMapper.inquirePermissionIdList(roleId);
    }

    @Override
    @Transactional
    public int createRolePermissionRelation(RolePermissionRelation rolePermissionRelation) {
        return rolePermissionRelationMapper.insertSelective(rolePermissionRelation);
    }

    @Override
    @Transactional
    public int deleteRolePermission(Integer roleId, Integer permissionId) {
        return rolePermissionRelationMapper.deleteByPrimaryKey(roleId, permissionId);
    }
}
