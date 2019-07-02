package com.handinglian.contentunion.param;

import com.handinglian.contentunion.entity.IntelligentSubDevice;
import lombok.Data;

import java.util.List;

@Data
public class IntelligentDeviceParam {
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
     * 中控主机id
     */
    private String extensionId;

    /**
     * 子菜单
     */
    private List<IntelligentSubDevice> intelligentSubDevice;

}
