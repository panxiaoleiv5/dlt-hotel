package com.handinglian.system.service;

import com.handinglian.system.dto.PermissionDto;
import com.handinglian.system.entity.Permission;

import java.util.List;

public interface PermissionService {
    List<PermissionDto > inquirePermissionList(Integer menuId);
}
