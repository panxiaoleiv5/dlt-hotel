package com.handinglian.contentunion.dto;

import com.handinglian.contentunion.entity.IntelligentSubDevice;
import lombok.Data;

import java.util.List;

@Data
public class IntelligentDeviceDto {
    /**
     * 智能设备id
     */
    private Integer intelligentDeviceId;

    /**
     * mac ip
     */
    private String macIp;

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
     * 分机id
     */
    private String extensionId;

    /**
     * 子菜单
     */
    private List<IntelligentSubDevice> intelligentSubDevice;

}
