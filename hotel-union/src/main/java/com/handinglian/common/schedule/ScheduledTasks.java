package com.handinglian.common.schedule;

import com.handinglian.common.exception.YyBizException;
import com.handinglian.common.utils.DateNewUtil;
import com.handinglian.common.utils.DateUtil;
import com.handinglian.common.utils.GeneralUtil;
import com.handinglian.extension.entity.CallRecord;
import com.handinglian.extension.service.CallRecordService;
import com.handinglian.yunyi.YunyiApiService;
import com.handinglian.yunyi.dto.YyCallRecordDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private YunyiApiService yunyiApiService;
    @Autowired
    private CallRecordService callRecordService;

    @Scheduled(cron = "0 0 11 * * *")
    @Transactional
    public void importCallRecord() throws IOException {
        String startDate = DateNewUtil.getDate(DateNewUtil.addDays(new Date(), -1))+" 00:00:00";
        String endDate = DateNewUtil.getDate(new Date())+" 00:00:00";
        YyCallRecordDto yyCallRecordDto = null;
        //获取云翌当天通话记录
        try {
            yyCallRecordDto = yunyiApiService.getCallRecord(null, null, startDate, endDate);
        } catch (YyBizException e){
            log.error("*******************云翌通话记录获取失败**********************");
        }

        //获取当天数据库中通话记录的reqid
        List<String> reqidDbList = callRecordService.inquireReqidByStartTime(startDate, endDate);

        List<YyCallRecordDto.MessageBean> messageBeans = yyCallRecordDto.getMessage();
        List<String> reqidParamList = new ArrayList<>();
        //获取当天通话记录的reqid
        messageBeans.forEach(messageBean -> {
            reqidParamList.add(messageBean.getReqid());
        });

        //获取需要添加到数据库的通话记录
        List<String> addList = GeneralUtil.getAddList(reqidParamList, reqidDbList);
        List<YyCallRecordDto.MessageBean> messageBeanAdd = messageBeans.stream().filter(messageBean -> addList.contains(messageBean.getReqid())).collect(Collectors.toList());

        List<CallRecord> callRecordList = new ArrayList<>();
        messageBeanAdd.forEach(messageBean -> {
            CallRecord callRecord = new CallRecord();
            callRecord.setStartTime(DateUtil.from(messageBean.getStarTime()));
            callRecord.setCallerNo(messageBean.getCallerNO());
            callRecord.setCalledNo(messageBean.getCalledNO());
            callRecord.setDurationTime(messageBean.getDurationTime());
            callRecord.setRecordStatus(Integer.valueOf(messageBean.getRecordStatus()));
            callRecord.setPhoneStatus(Integer.valueOf(messageBean.getStatus()));
            callRecord.setRecordFile(messageBean.getRecordFile());
            callRecord.setReqid(messageBean.getReqid());
            callRecordList.add(callRecord);
        });

        try {
            if (messageBeanAdd.size() > 0){
                callRecordService.createCallRecordBatch(callRecordList);
            }
        } catch (Exception e){
            log.error("****************云翌通话记录同步失败**************");
        }
    }
}
