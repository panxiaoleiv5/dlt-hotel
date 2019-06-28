package com.handinglian.system.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuPermissionDto {
    /**
     * 菜单id
     */
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    private List<MenuPermissionDto> childMenuPermissionDtos = new ArrayList<>();

    private List<PermissionDto> permissionDtos;

    public void addDepartment(MenuPermissionDto menuPermissionDto) {
        childMenuPermissionDtos.add(menuPermissionDto);
    }
}
