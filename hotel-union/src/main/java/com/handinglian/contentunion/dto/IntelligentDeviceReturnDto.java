package com.handinglian.contentunion.dto;

import lombok.Data;

import java.util.Date;

@Data
public class IntelligentDeviceReturnDto {
    /**
     * 主键id
     */
    private Integer intelligentDeviceId;

    /**
     * 产品id
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 设备位置
     */
    private String deviceAddress;

    /**
     * 中控主机id
     */
    private Integer centralHostId;

    /**
     * 中控主机名称
     */
    private String centralHostName;

    /**
     * mac ip
     */
    private String macIp;

    /**
     * 分机号
     */
    private String extensionNo;

    /**
     * 分机id
     */
    private Integer extensionId;

    /**
     * 在线状态
     */
    private Integer onlineState;

}
