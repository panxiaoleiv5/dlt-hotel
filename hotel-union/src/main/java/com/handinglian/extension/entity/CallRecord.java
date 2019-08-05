package com.handinglian.extension.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class CallRecord implements Serializable {
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
     * 录音文件名
     */
    private String recordFile;

    /**
     * 通话记录唯一性标识
     */
    private String reqid;

    /**
     * 通话费用
     */
    private BigDecimal callCost;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}