package com.handinglian.extension.param;

import lombok.Data;

import java.util.List;

@Data
public class SetMealCreateParam {
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
     * 分机号(List数组)
     */
    private List<String> extensionNos;

    /**
     * 通话类型详情(List数组)
     */
    private List<SetMealDetailCreateParam> setMealDetailCreateParams;
}
