package com.handinglian.system.service.impl;

import com.handinglian.system.dto.MenuPermissionDto;
import com.handinglian.system.entity.Menu;
import com.handinglian.system.mapper.MenuMapper;
import com.handinglian.system.service.MenuService;
import com.handinglian.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("/menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private PermissionService permissionService;

    @Override
    public List<MenuPermissionDto> inquireLevelMenuList(Integer level) {
        List<MenuPermissionDto> menuPermissionDtos = menuMapper.inquireLevelMenuList(level);
        menuPermissionDtos.forEach(menuPermissionDto -> {
            menuPermissionDto.setPermissionDtos(permissionService.inquirePermissionList(menuPermissionDto.getMenuId()));
        });
        return menuPermissionDtos;
    }
}
