package com.handinglian.extension.dto;

import lombok.Data;

@Data
public class ExtensionDto {
    /**
     * 主键
     */
    private Integer extensionId;

    /**
     * 分机号
     */
    private String extensionNo;

    /**
     * 房间号
     */
    private String roomNo;

    /**
     * 密码
     */
    private String passsword;

    /**
     * 注册ip
     */
    private String registerIp;

    /**
     * 录音状态 1开启 0关闭
     */
    private Integer recordState;
}
