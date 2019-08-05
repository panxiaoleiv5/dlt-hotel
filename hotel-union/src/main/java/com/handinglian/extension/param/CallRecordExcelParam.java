package com.handinglian.extension.param;


import lombok.Data;

@Data
public class CallRecordExcelParam {
    private String startTime = null;

    private String callerNo = null;

    private String calledNo = null;

    private String durationTime = null;

    private String recordStatus = null;

    private String phoneStatus = null;
}
