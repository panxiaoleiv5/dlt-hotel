package com.handinglian.contentunion.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ContextualModel implements Serializable {
    /**
     * 主键id
     */
    private Integer contextualModelId;

    /**
     * 模式名称
     */
    private String modelName;

    /**
     * 物品名称(子设备名称)
     */
    private String subDeviceName;

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