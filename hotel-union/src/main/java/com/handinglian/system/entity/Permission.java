package com.handinglian.system.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Permission implements Serializable {
    /**
     * 主键id
     */
    private Integer permissionId;

    /**
     * 菜单id
     */
    private Integer menuId;

    /**
     * 权限
     */
    private String permission;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 有效性 1有效 0无效
     */
    private Integer valid;

    private static final long serialVersionUID = 1L;
}