package com.handinglian.system.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Menu implements Serializable {
    /**
     * 主键id
     */
    private Integer menuId;

    /**
     * 菜单
     */
    private String menu;

    /**
     * 菜单描述
     */
    private String description;

    /**
     * 几级目录
     */
    private Integer level;

    /**
     * 上级id
     */
    private Integer parentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}