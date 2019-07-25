package com.handinglian.yunyi.impl;

import com.handinglian.common.constants.YyConstants;
import com.handinglian.common.utils.FastJsonUtil;
import com.handinglian.common.utils.HttpUtil;
import com.handinglian.common.utils.PropertyUtil;
import com.handinglian.yunyi.YunyiApiService;
import com.handinglian.yunyi.dto.*;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("yunyiApiService")
public class YunyiApiServiceImpl implements YunyiApiService {

    /**
    * 添加分机
    * @author pxl
    * @param extensionNo
    * @param roomNo
    * @param password
    * @param recordState
    * @return void
    * @date 2019/7/18 14:19
    */
    @Override
    public void addExtension(String extensionNo, String roomNo, String password, Integer recordState) throws IOException {
        String url = PropertyUtil.getKkApi(YyConstants.ADD_EXTENSION);
        Map<String, Object> params = new HashMap<>();
        params.put("exten_name",extensionNo);
        params.put("display_name",roomNo);
        params.put("exten_pwd",password);
        params.put("need_record",recordState);
        String response = HttpUtil.requestPostJsonYy(url, params);
        HttpUtil.responseProcessYy(response, YyConstants.ADD_EXTENSION, null);
    }

    /**
    * 查询分机
    * @author pxl
    * @param
    * @return java.util.List<com.handinglian.yunyi.dto.YyExtensionMessageDto>
    * @date 2019/7/18 14:19
    */
    @Override
    public List<YyExtensionMessageDto> getExtension(String extensionNo) throws IOException {
        String url = PropertyUtil.getKkApi(YyConstants.GET_EXTENSION);
        Map<String, Object> params = new HashMap<>();
        extensionNo = extensionNo == null?"":extensionNo;
        params.put("exten", extensionNo);
        String response = HttpUtil.requestPostJsonYy(url, params);
        System.out.println(response);
        YyGetExtensionJsonDto yyGetExtensionJsonDto = HttpUtil.responseProcessYy(response, YyConstants.GET_EXTENSION, YyGetExtensionJsonDto.class);
        return FastJsonUtil.ListToList(yyGetExtensionJsonDto.getMessage(), YyExtensionMessageDto.class);
    }

    @Override
    public String getTestExtension(String extensionNo) throws IOException {
        String url = PropertyUtil.getKkApi(YyConstants.GET_EXTENSION);
        Map<String, Object> params = new HashMap<>();
        extensionNo = extensionNo == null?"":extensionNo;
        params.put("exten", extensionNo);
        String response = HttpUtil.requestPostJsonYy(url, params);
        System.out.println(response);
        return response;
    }

    @Override
    public void deleteExtension(String extensionNo) throws IOException {
        //请求中控主机内设备列表
        String url = PropertyUtil.getKkApi(YyConstants.DELETE_EXTENSION);
        Map<String, Object> params = new HashMap<>();
        extensionNo = extensionNo == null?"":extensionNo;
        params.put("exten", extensionNo);
        String response = HttpUtil.requestPostJsonYy(url, params);
        HttpUtil.responseProcessYy(response, YyConstants.DELETE_EXTENSION, null);
    }

    @Override
    public void updateExtension(String extensionNo, String roomNo, String password, Integer recordState) throws IOException {
        String url = PropertyUtil.getKkApi(YyConstants.UPDATE_EXTENSION);
        Map<String, Object> params = new HashMap<>();
        params.put("exten_name",extensionNo);
        params.put("display_name",roomNo);
        params.put("exten_pwd",password);
        params.put("need_record",recordState);
        String response = HttpUtil.requestPostJsonYy(url, params);
        HttpUtil.responseProcessYy(response, YyConstants.UPDATE_EXTENSION, null);
    }

    @Override
    public YyExtensionRegisterStateDto getExtensionRegisterState(String extensionNo) throws IOException {
        String url = PropertyUtil.getKkApi(YyConstants.GET_REGISTER_STATE);
        Map<String, Object> params = new HashMap<>(16);
        extensionNo = extensionNo == null?"":extensionNo;
        params.put("exten", extensionNo);
        String response = HttpUtil.requestPostJsonYy(url, params);
        return HttpUtil.responseProcessYy(response, YyConstants.GET_REGISTER_STATE, YyExtensionRegisterStateDto.class);
    }

    @Override
    public YyCallRecordDto getCallRecord(String callerNo, String calledNo, String startTime, String endTime) throws IOException {
        String url = PropertyUtil.getKkApi(YyConstants.GET_CALL_RECORD);
        Map<String, Object> params = new HashMap<>();
        params.put("callerNO", callerNo);
        params.put("calledNO", calledNo);
        params.put("starTime", startTime);
        params.put("endTime", endTime);
        String response = HttpUtil.requestPostJsonYy(url, params);
        return HttpUtil.responseProcessYy(response, YyConstants.GET_CALL_RECORD, YyCallRecordDto.class);
    }

    @Override
    public void downloadRecording(String reqid) throws IOException {
        String url = PropertyUtil.getKkApi(YyConstants.DOWNLOAD_RECORDING);
        Map<String, Object> params = new HashMap<>(16);
        params.put("reqid", reqid);
        String response = HttpUtil.requestPostJsonYy(url, params);
        HttpUtil.responseProcessYy(response, YyConstants.DOWNLOAD_RECORDING, null);
    }

    @Test
    public void test() throws IOException {
        getCallRecord(null,null,"2019-02-06 12:55:30", "2019-09-06 18:55:30");
    }

}
