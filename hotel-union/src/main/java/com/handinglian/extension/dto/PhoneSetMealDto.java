package com.handinglian.extension.dto;

import lombok.Data;

import java.util.List;

@Data
public class PhoneSetMealDto {
    /**
     * 主键id
     */
    private Integer phoneSetMealId;

    /**
     * 套餐名称
     */
    private String setMealName;

    /**
     * 1默认套餐 0非默认
     */
    private Integer isDefault;

    /**
     * 通话类型详情
     */
    private List<PhoneSetMealDetailDto> phoneSetMealDetailDtos;
}
