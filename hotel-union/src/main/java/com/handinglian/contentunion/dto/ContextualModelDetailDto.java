package com.handinglian.contentunion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ContextualModelDetailDto {
    /**
     * 主键id
     */
    private Integer contextualModelId;

    /**
     * 模式名称
     */
    private String modelName;

    /**
     * 物品名称(子设备名称，用逗号分隔)
     */
    private String subDeviceName;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
