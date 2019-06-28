package com.handinglian.kongke.dto;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: pxl
 * @create: 2019/6/5 17:14
 */
@Data
public class KkZigbeeDevicesJsonDto {
    private String mac;
    private String gwMac;
    private String onlineState;
    private String lastJoinTime;
    private String version;
    private int productId;
    private List<KkChannelDevicesJsonDto> channelDevices;
}
