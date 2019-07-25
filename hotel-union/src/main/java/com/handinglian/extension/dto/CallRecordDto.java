package com.handinglian.extension.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CallRecordDto {
    /**
     * 主键
     */
    private Integer callRecordId;

    /**
     * 通话时间
     */
    private Date startTime;

    /**
     * 主叫号码
     */
    private String callerNo;

    /**
     * 被叫号码
     */
    private String calledNo;

    /**
     * 通话时长
     */
    private String durationTime;

    /**
     * 呼叫类型 0 打入 1 是打出 -1 内线
     */
    private Integer recordStatus;

    /**
     * 通话状态 0,呼叫失败,1.无应答,2已应答
     */
    private Integer phoneStatus;

    /**
     * 通话记录唯一性标识
     */
    private String reqid;
}
