package com.handinglian.extension.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.utils.DateUtil;
import com.handinglian.common.utils.FastJsonUtil;
import com.handinglian.common.utils.GeneralUtil;
import com.handinglian.common.utils.StringUtil;
import com.handinglian.extension.dto.CallRecordDto;
import com.handinglian.extension.entity.*;
import com.handinglian.extension.enums.SetMealCallTypeEnum;
import com.handinglian.extension.mapper.CallRecordMapper;
import com.handinglian.extension.mapper.PhoneSetMealDetailMapper;
import com.handinglian.extension.param.CallRecordCreateParam;
import com.handinglian.extension.service.*;
import com.handinglian.kongke.dto.KkZigbeeDevicesJsonDto;
import com.handinglian.system.entity.FileInfo;
import com.handinglian.system.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service("callRecordService")
public class CallRecordServiceImpl implements CallRecordService {
    @Autowired
    private CallRecordMapper callRecordMapper;
    @Autowired
    private FileInfoService fileInfoService;
    @Autowired
    private PhoneSetMealDetailService phoneSetMealDetailService;
    @Autowired
    private PhoneSetMealService phoneSetMealService;
    @Autowired
    private ExtensionService extensionService;
    @Autowired
    private AreaCodeService areaCodeService;

    @Override
    public PageInfo<CallRecordDto> inquireCallRecordPageList(String startDate, String endDate, String callerNo, String calledNo, Integer recordStatus, Integer phoneStatus, Integer hasRecord, Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 1 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageIndex, pageSize);

        List<CallRecord> callRecordList = callRecordMapper.inquireCallRecordPageList(DateUtil.from(startDate), DateUtil.from(endDate), callerNo, calledNo, recordStatus, phoneStatus, hasRecord);
        List<CallRecordDto> callRecordDtos = FastJsonUtil.ListToList(callRecordList, CallRecordDto.class);
        return new PageInfo(callRecordDtos);
    }

    @Override
    public List<FileInfo> inquireCallRecordingList(List<String> reqids) {
        List<String> fileUuids = callRecordMapper.findFileUuidByReqidIn(reqids);
        fileUuids = fileUuids.stream().filter(fileUuid -> StringUtil.isNotBlank(fileUuid)).collect(Collectors.toList());
        return fileInfoService.inquireFileInfoList(fileUuids);
    }

    @Override
    @Transactional
    public int createCallRecord(List<CallRecordCreateParam> callRecordCreateParams) {
        List<CallRecord> callRecordList = new ArrayList<>();
        callRecordCreateParams.forEach(callRecordCreateParam -> {
            CallRecord callRecord = convertCallRecordCreateParamToCallRecord(callRecordCreateParam);

            Extension extension = extensionService.loadExtensionByExtensionNo(callRecord.getCallerNo());
            if (extension != null){
                List<PhoneSetMealDetail> phoneSetMealDetailList = phoneSetMealDetailService.inquirePhoneSetMealDetailList(extension.getSetMealId());
                PhoneSetMeal phoneSetMeal = phoneSetMealService.loadPhoneSetMeal(extension.getSetMealId());
                //确定该通话属于哪个套餐
                SetMealCallTypeEnum setMealCallTypeEnum = judgePhoneSetMealDetail(callRecord.getCallerNo(), phoneSetMeal.getAreaCodeId());
                //计算通话费用
                if (callRecordCreateParam.getRecordStatus() != 1){
                    callRecord.setCallCost(new BigDecimal(0));
                } else {
                    BigDecimal callCost = calculateCallCost(setMealCallTypeEnum, phoneSetMealDetailList, callRecordCreateParam.getDurationTime());
                    callRecord.setCallCost(callCost);
                }
            }

            callRecordList.add(callRecord);
        });

        return callRecordMapper.batchInsert(callRecordList);
    }

    private CallRecord convertCallRecordCreateParamToCallRecord(CallRecordCreateParam callRecordCreateParam) {
        CallRecord callRecord = new CallRecord();
        callRecord.setStartTime(DateUtil.from(callRecordCreateParam.getStarTime()));
        callRecord.setCallerNo(callRecordCreateParam.getCallerNO());
        callRecord.setCalledNo(callRecordCreateParam.getCalledNO());
        callRecord.setDurationTime(callRecordCreateParam.getDurationTime().toString());
        callRecord.setRecordStatus(callRecordCreateParam.getRecordStatus());
        Integer phoneStatus = callRecordCreateParam.getDurationTime() == 0?1:2;
        callRecord.setPhoneStatus(phoneStatus);
        callRecord.setRecordFile(callRecordCreateParam.getRecordFile());
        callRecord.setReqid(callRecordCreateParam.getReqid());
        callRecord.setCallCost(new BigDecimal(0));
        callRecord.setCreateTime(new Date());
        return callRecord;
    }


    private BigDecimal calculateCallCost(SetMealCallTypeEnum setMealCallTypeEnum, List<PhoneSetMealDetail> phoneSetMealDetailList, Integer durationTime){
        BigDecimal callCost = new BigDecimal(0);
        Optional<PhoneSetMealDetail> optional = phoneSetMealDetailList.stream().filter(phoneSetMealDetail1 -> phoneSetMealDetail1.getType().equals(setMealCallTypeEnum.getKey())).findFirst();
        if (optional.isPresent()){
            PhoneSetMealDetail phoneSetMealDetail = optional.get();
            if (durationTime <= phoneSetMealDetail.getBasicTime()){
                callCost = phoneSetMealDetail.getBasicRate();
            } else {
                Integer extraTime = durationTime - phoneSetMealDetail.getBasicTime();
                Integer minute = extraTime/phoneSetMealDetail.getMeteringTime()+1;
                callCost = phoneSetMealDetail.getBasicRate().add(phoneSetMealDetail.getMeteringRate().multiply(new BigDecimal(minute)));
            }
        }
        return callCost;
    }

    private SetMealCallTypeEnum judgePhoneSetMealDetail(String calledNo, Integer areaCodeId){
        SetMealCallTypeEnum setMealCallTypeEnum;
        AreaCode areaCode = areaCodeService.loadAreaCode(areaCodeId);
        boolean isLocalFixed = calledNo.startsWith(areaCode.getAreaCode())||calledNo.length() == 7||calledNo.length() == 8;

        List<String> areaCodeList = areaCodeService.inquireAreaCodeList();
        boolean isLongDistanceFixed = (areaCodeList.contains(calledNo.substring(0,3))||areaCodeList.contains(calledNo.substring(0,4)))
                &&!isLocalFixed;

        boolean isInland = (calledNo.length() == 11&& GeneralUtil.isMobile(calledNo))
                ||(calledNo.length() == 12&&GeneralUtil.isMobile(calledNo.substring(1)))
                ||(calledNo.length() == 15&&GeneralUtil.isMobile(calledNo.substring(4)));

        boolean isHongKongAoMenTaiWan = calledNo.startsWith("00852")||calledNo.startsWith("00853")||calledNo.startsWith("00886");

        boolean isUKFrance = calledNo.startsWith("0044")||calledNo.startsWith("0033")||calledNo.startsWith("0039")
                ||calledNo.startsWith("0049")||calledNo.startsWith("0064")||calledNo.startsWith("0082")
                ||calledNo.startsWith("0081")||calledNo.startsWith("0061")||calledNo.startsWith("0065")
                ||calledNo.startsWith("0060")||calledNo.startsWith("0066")||calledNo.startsWith("0062")
                ||calledNo.startsWith("0063");

        boolean isOther = calledNo.startsWith("00")&&!isHongKongAoMenTaiWan&&!isUKFrance&&!calledNo.startsWith("001");

        if (isLocalFixed){
            setMealCallTypeEnum = SetMealCallTypeEnum.LOCAL_FIXED_PHONE;
        }else if (isLongDistanceFixed){
            setMealCallTypeEnum = SetMealCallTypeEnum.LOCAL_FIXED_PHONE;
        } else if (isInland){
            setMealCallTypeEnum = SetMealCallTypeEnum.LOCAL_MOBILE_PHONE;
        } else if (isHongKongAoMenTaiWan){
            setMealCallTypeEnum = SetMealCallTypeEnum.HONGKONG_AOMEN_TAIWAN;
        } else if (calledNo.startsWith("001")){
            setMealCallTypeEnum = SetMealCallTypeEnum.AMERICA_CANADA;
        } else if (isUKFrance){
            setMealCallTypeEnum = SetMealCallTypeEnum.UK_FRANCE;
        } else if (isOther){
            setMealCallTypeEnum = SetMealCallTypeEnum.OTHER;
        } else {
            setMealCallTypeEnum = SetMealCallTypeEnum.UNKNOWN;
        }
        return setMealCallTypeEnum;
    }


    @Override
    public List<String> inquireReqidByStartTime(String startDate, String endDate) {
        return callRecordMapper.findReqidByStartTimeBetween(DateUtil.from(startDate), DateUtil.from(endDate));
    }

    @Override
    @Transactional
    public int createCallRecordBatch(List<CallRecord> callRecordList) {
        return callRecordMapper.batchInsert(callRecordList);
    }
}
