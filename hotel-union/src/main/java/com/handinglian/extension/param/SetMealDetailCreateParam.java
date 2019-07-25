package com.handinglian.extension.param;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SetMealDetailCreateParam {
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
}
