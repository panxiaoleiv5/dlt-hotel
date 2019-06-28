package com.handinglian.contentunion.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class IntelligentDevice implements Serializable {
    /**
     * 主键id
     */
    private Integer intelligentDeviceId;

    /**
     * mac ip
     */
    private String macIp;

    /**
     * 所属网关 mac 地址
     */
    private String gwMac;

    /**
     * 产品id
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 控客设备类型
     */
    private String kkType;

    /**
     * 设备位置
     */
    private String deviceAddress;

    /**
     * 中控主机id
     */
    private Integer centralHostId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否红外线设备 1是 0不是
     */
    private Integer isInfrared;

    /**
     * 有效性 1有效 0无效
     */
    private Integer valid;

    private static final long serialVersionUID = 1L;
}