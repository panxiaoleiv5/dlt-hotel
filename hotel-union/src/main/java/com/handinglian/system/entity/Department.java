package com.handinglian.system.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Department implements Serializable {
    /**
     * 主键id
     */
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

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