package com.handinglian.extension.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class PhoneSetMeal implements Serializable {
    /**
     * 主键id
     */
    private Integer phoneSetMealId;

    /**
     * 套餐名称
     */
    private String setMealName;

    /**
     * 区号id
     */
    private Integer areaCodeId;

    /**
     * 1默认套餐 0非默认
     */
    private Integer isDefault;

    /**
     * 有效性 1有效 0无效
     */
    private Integer valid;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}