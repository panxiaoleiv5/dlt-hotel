package com.handinglian.extension.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

@Data
public class CallRecordExcel {
    @Excel(name = "时间", width = 20)
    private String startTime = null;

    @Excel(name = "主叫")
    private String callerNo = null;

    @Excel(name = "被叫")
    private String calledNo = null;

    @Excel(name = "时长")
    private String durationTime = null;

    @Excel(name = "类型")
    private String recordStatus = null;

    @Excel(name = "状态")
    private String phoneStatus = null;

}
