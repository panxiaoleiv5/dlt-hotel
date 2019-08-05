package com.handinglian.contentunion.dto;

import lombok.Data;

@Data
public class ContextualDeviceDto {
    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 是否被选中
     */
    private boolean isSelected;
}
