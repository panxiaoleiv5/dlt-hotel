package com.handinglian.contentunion.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mybatis Generator 2019/05/30
 */
@Data
public class IntelligentSubDeviceDto implements Serializable {
    /**
     * 主键id
     */
    private Integer intelligentSubDeviceId;

    /**
     * 智能父设备id
     */
    private Integer intelligentDeviceId;

    /**
     * 子设备名称
     */
    private String subProductName;

    /**
     * 设备位置
     */
    private String address;

    /**
     * 功率
     */
    private Integer power;

    /**
     * 通道
     */
    private Integer channel;

    private static final long serialVersionUID = 1L;

    public IntelligentSubDeviceDto() {
    }

    protected boolean canEqual(Object other) {
        return other instanceof IntelligentSubDeviceDto;
    }
}