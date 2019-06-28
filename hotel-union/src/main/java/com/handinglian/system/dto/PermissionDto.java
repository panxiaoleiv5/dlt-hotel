package com.handinglian.system.dto;

import lombok.Data;

@Data
public class PermissionDto {
    /**
     * 权限id
     */
    private Integer permissionId;

    /**
     * 菜单名称
     */
    private String permissionName;
}
