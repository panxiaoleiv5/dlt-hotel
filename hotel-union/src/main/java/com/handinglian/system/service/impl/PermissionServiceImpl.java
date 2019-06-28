package com.handinglian.system.service.impl;

import com.handinglian.system.dto.PermissionDto;
import com.handinglian.system.entity.Permission;
import com.handinglian.system.mapper.PermissionMapper;
import com.handinglian.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionDto > inquirePermissionList(Integer menuId) {
        return permissionMapper.findByMenuId(menuId);
    }
}
