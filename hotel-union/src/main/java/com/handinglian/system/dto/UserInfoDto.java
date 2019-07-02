package com.handinglian.system.dto;

import lombok.Data;

@Data
public class UserInfoDto {
    /**
     * 主键id
     */
    private Integer userId;

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 职位
     */
    private String position;

    /**
     * 工号
     */
    private String jobNum;

    /**
     * 手机号
     */
    private String mobilePhoneNum;

    /**
     * 邮箱
     */
    private String eamil;

}
