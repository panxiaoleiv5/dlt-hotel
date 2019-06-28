package com.handinglian.system.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class UserInfo implements Serializable {
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

    /**
     * 密码
     */
    private String password;

    /**
     * 加密随机字串
     */
    private String salt;

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