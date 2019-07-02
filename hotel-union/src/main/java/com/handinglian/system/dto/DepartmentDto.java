package com.handinglian.system.dto;

import lombok.Data;

@Data
public class DepartmentDto {
    /**
     * 主键id
     */
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;
}
