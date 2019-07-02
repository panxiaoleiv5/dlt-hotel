package com.handinglian.system.dto;

import lombok.Data;

@Data
public class RoleDto {
    /**
     * 主键id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 权限id
     */
    private String permissionIds;
}
