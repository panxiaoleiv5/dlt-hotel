package com.handinglian.extension.controller;

import com.github.pagehelper.PageInfo;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.factory.ResultDataFactory;
import com.handinglian.common.utils.UserDownZipUtils;
import com.handinglian.common.utils.UuidUtil;
import com.handinglian.extension.dto.CallRecordDto;
import com.handinglian.extension.param.PushCallRecordParam;
import com.handinglian.extension.service.CallRecordService;
import com.handinglian.system.entity.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/callrecord")
public class CallRecordController {

    @Autowired
    private CallRecordService callRecordService;
    @Value("${file.location}")
    private String fileLocation;
    @Value("${download.url}")
    private String downloadUrl;

    /**
     * 获取通话记录分页列表
     */
    @GetMapping("/inquireCallRecordPageList")
    public ResultData<PageInfo<CallRecordDto>> inquireCallRecordPageList(String startDate, String endDate, String callerNo, String calledNo, Integer recordStatus, Integer phoneStatus, Integer hasRecord, Integer pageIndex, Integer pageSize) {
        PageInfo<CallRecordDto> recordDtoPageInfo = callRecordService.inquireCallRecordPageList(startDate, endDate, callerNo, calledNo, recordStatus, phoneStatus, hasRecord, pageIndex, pageSize);
        return ResultDataFactory.generateSuccessResultData(recordDtoPageInfo);
    }

    @PostMapping("/downloadRecording")
    public ResultData downloadRecording(@RequestBody List<String> reqids) {

        List<FileInfo> fileInfoList = callRecordService.inquireCallRecordingList(reqids);
        List<File> fileList = new LinkedList<>();//暂存文件列表
        String path = fileLocation; //得到zip文件的生成地址
        File fi= new File(path);//构造zip文件存放的目录
        if(!fi.exists()){
            fi.mkdir();
        }
        String fileName = UuidUtil.randomUUID() +".zip";
        if(fileInfoList!=null && fileInfoList.size()>0){
            for (FileInfo fileInfo : fileInfoList) {
                // 构建本地文件
                File file = new File(fileInfo.getRelativePath());
                // 将本地文件加入fileList 文件列表
                fileList.add(file);

            }
        }
        //构造压缩文件
        String zipFileName = path+"/"+fileName;


        try {
            UserDownZipUtils.downZipManyFile(fileList, zipFileName); // 将文件列表写入压缩文件中
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultDataFactory.generateSuccessResultData(downloadUrl+fileName);
    }

    @PostMapping("/pushCdrInfo")
    public String pushCdrInfo(@RequestBody PushCallRecordParam pushCallRecordParam) {
        int amount = callRecordService.createCallRecord(pushCallRecordParam.getCdr());
        return amount == 0?"failure":"success";
    }
}
