package com.handinglian.system.service;

import com.handinglian.system.dto.MenuPermissionDto;
import com.handinglian.system.entity.Menu;

import java.util.List;

public interface MenuService {
    List<MenuPermissionDto> inquireLevelMenuList(Integer level);
}
