package com.handinglian.system.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class RolePermissionRelation implements Serializable {
    private Integer roleId;

    private Integer permissionId;

    private static final long serialVersionUID = 1L;
}