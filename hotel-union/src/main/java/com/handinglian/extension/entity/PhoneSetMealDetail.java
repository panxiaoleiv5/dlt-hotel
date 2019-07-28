package com.handinglian.extension.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class PhoneSetMealDetail implements Serializable {
    /**
     * 主键id
     */
    private Integer phoneSetMealDetailId;

    /**
     * 套餐id
     */
    private Integer phoneSetMealId;

    /**
     * 基本时
     */
    private Integer basicTime;

    /**
     * 基本费率
     */
    private BigDecimal basicRate;

    /**
     * 计次时长
     */
    private Integer meteringTime;

    /**
     * 计次费率
     */
    private BigDecimal meteringRate;

    /**
     * 类型
     */
    private Integer type;

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