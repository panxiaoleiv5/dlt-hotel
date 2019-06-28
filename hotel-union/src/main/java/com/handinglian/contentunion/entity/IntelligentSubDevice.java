package com.handinglian.contentunion.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class IntelligentSubDevice implements Serializable {
    /**
     * 主键id
     */
    private Integer intelligentSubDeviceId;

    /**
     * 智能父设备id
     */
    private Integer intelligentDeviceId;

    /**
     * 控客设备id
     */
    private Integer kkDeviceId;

    /**
     * 子设备名称
     */
    private String subDeviceName;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}