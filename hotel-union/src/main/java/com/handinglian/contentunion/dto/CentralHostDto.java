package com.handinglian.contentunion.dto;

import lombok.Data;

@Data
public class CentralHostDto {
    /**
     * 主键
     */
    private Integer centralHostId;

    /**
     * 主机位置
     */
    private String hostAddress;

    /**
     * 识别码
     */
    private String macIp;

    /**
     * 中控主机名称
     */
    private String centralHostName;

}
