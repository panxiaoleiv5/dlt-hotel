package com.handinglian.extension.service;


import com.github.pagehelper.PageInfo;
import com.handinglian.extension.dto.CallRecordDto;
import com.handinglian.extension.entity.CallRecord;
import com.handinglian.extension.param.CallRecordCreateParam;
import com.handinglian.system.entity.FileInfo;

import java.util.List;

public interface CallRecordService {
    PageInfo<CallRecordDto> inquireCallRecordPageList(String startDate, String endDate, String callerNo, String calledNo, Integer recordStatus, Integer phoneStatus, Integer hasRecord, Integer pageIndex, Integer pageSize);

    List<FileInfo> inquireCallRecordingList(List<String> reqids);

    int createCallRecord(List<CallRecordCreateParam> callRecordCreateParams);

    List<String> inquireReqidByStartTime(String startDate, String endDate);

    int createCallRecordBatch(List<CallRecord> callRecordList);
}
