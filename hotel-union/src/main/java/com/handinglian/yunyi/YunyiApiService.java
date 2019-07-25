package com.handinglian.yunyi;

import com.handinglian.yunyi.dto.TestDto;
import com.handinglian.yunyi.dto.YyCallRecordDto;
import com.handinglian.yunyi.dto.YyExtensionMessageDto;
import com.handinglian.yunyi.dto.YyExtensionRegisterStateDto;

import java.io.IOException;
import java.util.List;

public interface YunyiApiService {
    /**
    * 添加分机
    * @author pxl
    * @param extensionNo 分机号
    * @param roomNo 房间号
    * @param password 分机密码
    * @param recordState 录音状态 1录音 0不录音
    * @return void
    * @date 2019/7/17 14:49
    */
    void addExtension(String extensionNo, String roomNo, String password, Integer recordState) throws IOException;

    /**
    * 查询分机
    * @author pxl
    * @param extensionNo 分机号，如果为null或空字符串，则查询全部分机
    * @return java.util.List<com.handinglian.yunyi.dto.YyExtensionMessageDto>
    * @date 2019/7/18 14:30
    */
    List<YyExtensionMessageDto> getExtension(String extensionNo) throws IOException;

    String getTestExtension(String extensionNo) throws IOException;

    /**
    * 删除分机
    * @author pxl
    * @param extensionNo
    * @return void
    * @date 2019/7/18 14:33
    */
    void deleteExtension(String extensionNo) throws IOException;

    /**
     * 修改分机
     * @author pxl
     * @param extensionNo 分机号
     * @param roomNo 房间号
     * @param password 分机密码
     * @param recordState 录音状态 1录音 0不录音
     * @return void
     * @date 2019/7/17 14:49
     */
    void updateExtension(String extensionNo, String roomNo, String password, Integer recordState) throws IOException;

    /**
     * 查询分机注册转态
     * @author pxl
     * @param extensionNo 分机号，如果为null或空字符串，则查询全部分机注册状态
     * @return java.util.List<com.handinglian.yunyi.dto.YyExtensionMessageDto>
     * @date 2019/7/18 14:30
     */
    YyExtensionRegisterStateDto getExtensionRegisterState(String extensionNo) throws IOException;

    /**
    * 查询通话记录
    * @author pxl
    * @param callerNo
    * @param calledNo
    * @param startTime
    * @param endTime
    * @return com.handinglian.yunyi.dto.YyCallRecordDto
    * @date 2019/7/23 10:30
    */
    YyCallRecordDto getCallRecord(String callerNo, String calledNo, String startTime, String endTime) throws IOException;

    /**
    * 下载录音
    * @author pxl
    * @param reqid
    * @return void
    * @date 2019/7/23 10:31
    */
    void downloadRecording(String reqid) throws IOException;
}
