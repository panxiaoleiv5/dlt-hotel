package com.handinglian.contentunion.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class IntelligentExtensionRelation implements Serializable {
    /**
     * 主键
     */
    private Integer intelligentExtensionRelationId;

    /**
     * 智能设备id
     */
    private Integer intelligentDeviceId;

    /**
     * 分机id
     */
    private Integer extensionId;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}