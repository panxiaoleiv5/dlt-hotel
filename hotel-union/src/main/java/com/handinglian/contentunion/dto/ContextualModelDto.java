package com.handinglian.contentunion.dto;

import lombok.Data;

@Data
public class ContextualModelDto {
    /**
     * 主键id
     */
    private Integer contextualModelId;

    /**
     * 模式名称
     */
    private String modelName;

    /**
     * 物品名称(子设备名称)
     */
    private String subDeviceName;

    /**
     * 控客设备名称
     */
    private String kkDeviceNames;
}
