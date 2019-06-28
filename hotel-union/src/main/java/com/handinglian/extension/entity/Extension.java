package com.handinglian.extension.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Extension implements Serializable {
    /**
     * 主键
     */
    private Integer extensionId;

    /**
     * 分机号
     */
    private String extensionNo;

    /**
     * 房间号
     */
    private String roomNo;

    /**
     * 密码
     */
    private String passsword;

    /**
     * 随机数
     */
    private String randomCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 有效性 1有效 -1无效
     */
    private Integer valid;

    private static final long serialVersionUID = 1L;
}