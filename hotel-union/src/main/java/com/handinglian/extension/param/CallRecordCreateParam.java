package com.handinglian.extension.param;

import lombok.Data;

@Data
public class CallRecordCreateParam {
    private String reqid;

    private Integer recordStatus;

    private String callerNO;

    private String calledNO;

    private String starTime;

    private Integer durationTime;

    private String recordFile;
}
