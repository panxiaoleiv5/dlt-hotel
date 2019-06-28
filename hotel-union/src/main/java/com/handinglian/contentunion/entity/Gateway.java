package com.handinglian.contentunion.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class Gateway implements Serializable {
    /**
     * 主键id
     */
    private Integer gatewayId;

    /**
     * 中控主机id
     */
    private Integer centralHostId;

    /**
     * 网关 id
     */
    private Integer gwId;

    /**
     * 网关名称
     */
    private String gwName;

    /**
     * 网关mac
     */
    private String gwMac;

    /**
     * 网关 id
     */
    private String gwIp;

    /**
     * 网关类型。0：udp 网关；1：tcp 网关；2：串口一体化网关；3：POE 网关
     */
    private Integer gwType;

    /**
     * 网关当前运行版本
     */
    private String gwCurVersion;

    /**
     * 网关已下载版本，用于升级
     */
    private String gwDownloadVersion;

    private static final long serialVersionUID = 1L;
}