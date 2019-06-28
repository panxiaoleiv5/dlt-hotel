package com.handinglian.kongke.dto;

import lombok.Data;

/**
 * @description:
 * @author: pxl
 * @create: 2019/6/11 13:52
 */
@Data
public class KkDeviceJsonDto {
    private int id;
    private String type;
    private String lastJoinTime;
    private String mac;
    private int channel;
    private String gwMac;

}
