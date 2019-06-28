package com.handinglian.contentunion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.handinglian.contentunion.entity.IntelligentSubDevice;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* Created by Mybatis Generator 2019/05/28
*/
@Data
public class IntelligentDeviceDetailDto implements Serializable {
    /**
	* 主键id
	*/
    private Integer intelligentDeviceId;

    /**
	* 设备ip
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
     * 智能子设备
     */
    private List<IntelligentSubDevice> intelligentSubDeviceList;

    private static final long serialVersionUID = 1L;
}