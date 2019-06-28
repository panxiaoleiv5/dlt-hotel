package com.handinglian.kongke.service.impl;

import com.alibaba.fastjson.JSON;
import com.handinglian.common.constants.KkConstants;
import com.handinglian.common.utils.FastJsonUtil;
import com.handinglian.common.utils.HttpUtil;
import com.handinglian.common.utils.PropertyUtil;
import com.handinglian.kongke.dto.*;
import com.handinglian.kongke.service.KongkeApiService;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @description:
 * @author: pxl
 * @create: 2019/6/5 16:42
 */
@Service("kongkeApiService")
public class KongkeApiServiceImpl implements KongkeApiService {
    @Override
    public void deleteNotZigbeeDevice(String ccuId, String kkDeviceId, String kkDeviceType) throws IOException, DocumentException {
        String url = PropertyUtil.getKkApi(KkConstants.DELETE_DEVICE);
        url = url.replace(KkConstants.CCU_ID, ccuId);
        Map<String, Object> subParams = new HashMap<>();
        subParams.put("id",kkDeviceId);
        subParams.put("type",kkDeviceType);

        Map<String, Object> params = new HashMap<>();
        params.put("action", KkConstants.DEL_DEVICE);
        params.put("actionArg", subParams);
        String response = HttpUtil.requestPostJson(url, params);

        HttpUtil.responseProcess(response, KkConstants.DEL_DEVICE, null);
    }

    @Override
    public void deleteGwWhiteList(String ccuId, String gwId, String macIp) throws IOException, DocumentException {
        String url = PropertyUtil.getKkApi(KkConstants.NETWORK_CONFIG);
        url = url.replace(KkConstants.CCU_ID, ccuId);
        url = url.replace(KkConstants.GW_ID, gwId);
        Map<String, Object> subParams = new HashMap<>();
        subParams.put("devMacs",new String[]{macIp});

        Map<String, Object> params = new HashMap<>();
        params.put("action", KkConstants.DEL_GW_WHITE_LIST);
        params.put("actionArg", subParams);
        String response = HttpUtil.requestPostJson(url, params);
        HttpUtil.responseProcess(response, KkConstants.DEL_GW_WHITE_LIST, null);
    }

    @Override
    public KkWhiteListStateJsonDto getGwWorkmode(String ccuId, String gwId) throws IOException, DocumentException {
        String url = PropertyUtil.getKkApi(KkConstants.NETWORK_CONFIG);
        url = url.replace(KkConstants.CCU_ID, ccuId);
        url = url.replace(KkConstants.GW_ID, gwId);
        Map<String, Object> params = new HashMap<>(16);
        params.put("action", KkConstants.GET_GW_WORKMODE);
        params.put("actionArg",null);
        String response = HttpUtil.requestPostJson(url, params);

        return HttpUtil.responseProcess(response, "network.config", KkWhiteListStateJsonDto.class);
    }

    @Override
    public void setGwWorkmode(String ccuId, String gwId) throws IOException, DocumentException {
        String url = PropertyUtil.getKkApi(KkConstants.NETWORK_CONFIG);
        url = url.replace(KkConstants.CCU_ID, ccuId);
        url = url.replace(KkConstants.GW_ID, gwId);

        Map<String, Object> subParams = new HashMap<>(16);
        subParams.put("mode", 1);

        Map<String, Object> params = new HashMap<>(16);
        params.put("action", KkConstants.SET_GW_WORKMODE);
        params.put("actionArg", subParams);

        String response = HttpUtil.requestPostJson(url, params);

        String key = new StringBuffer(KkConstants.NETWORK_CONFIG).append(" ").append(KkConstants.SET_GW_WORKMODE).toString();
        HttpUtil.responseProcess(response, key, null);
    }

    @Override
    public KkCreateCentralHostJsonDto bindingCentralHost(String devQRCode) throws IOException, DocumentException {
        String url = PropertyUtil.getKkApi(KkConstants.BINDING_HOST);
        Map<String, Object> map = new HashMap(16);
        map.put("devQRCode", devQRCode);
        String response = HttpUtil.requestPostJson(url, map);

        return HttpUtil.responseProcess(response, KkConstants.BINDING_HOST, KkCreateCentralHostJsonDto.class);
    }

    @Override
    public void unbindingCentralHost(String ccuId) throws IOException, DocumentException {
        String url = PropertyUtil.getKkApi(KkConstants.UNBINDING_HOST);
        url = url.replace(KkConstants.CCU_ID, ccuId);
        String response = HttpUtil.requestDelete(url);

        HttpUtil.responseProcess(response, KkConstants.UNBINDING_HOST, null);
    }

    @Override
    public KkHostBaseInfoJsonDto getHostBaseInfo(String ccuId) throws IOException {
        String url = PropertyUtil.getKkApi(KkConstants.GET_HOST_BASEINFO);
        url = url.replace(KkConstants.CCU_ID, ccuId);
        String response = HttpUtil.requestGet(url);

        return HttpUtil.responseProcess(response, KkConstants.GET_HOST_BASEINFO, KkHostBaseInfoJsonDto.class);
    }

    @Override
    public List<KkZigbeeDevicesJsonDto> getNodeList(String ccuId) throws IOException {
        //请求中控主机内节点列表
        String url = PropertyUtil.getKkApi(KkConstants.GET_NODE_LIST);
        url = url.replace(KkConstants.CCU_ID, ccuId);
        String response = HttpUtil.requestGet(url);
        KkIntelligentNodeJsonDto kkIntelligentNodeJsonDto = HttpUtil.responseProcess(response, KkConstants.GET_NODE_LIST, KkIntelligentNodeJsonDto.class);
        List<KkIntelligentNodeJsonDto.ZigbeeDevicesBean> zigbeeDevicesBeans = kkIntelligentNodeJsonDto.getZigbeeDevices();

        List<KkZigbeeDevicesJsonDto> zigbeeDevicesJsonDtos = FastJsonUtil.ListToList(zigbeeDevicesBeans, KkZigbeeDevicesJsonDto.class);
        zigbeeDevicesJsonDtos.forEach(zigbeeDevicesJsonDto->{
            List<KkChannelDevicesJsonDto> channelDevicesJsonDtos = zigbeeDevicesJsonDto.getChannelDevices();
            channelDevicesJsonDtos.forEach(channelDevicesJsonDto->{
                KkChannelDevicesJsonDto.StatusBean statusBean = channelDevicesJsonDto.getStatus();
                channelDevicesJsonDto.setOnlineState(statusBean.isOn());
            });
        });
        return zigbeeDevicesJsonDtos;
    }

    @Override
    public List<KkDeviceJsonDto> getDeviceList(String ccuId) throws IOException {
        //请求中控主机内设备列表
        String url = PropertyUtil.getKkApi(KkConstants.GET_DEVICE_LIST);
        url = url.replace(KkConstants.CCU_ID, ccuId);
        String response = HttpUtil.requestGet(url);
        List<KkIntelligentDeviceJsonDto> kkIntelligentDeviceJsonDtos = HttpUtil.responseListProcess(response, KkConstants.GET_DEVICE_LIST, KkIntelligentDeviceJsonDto.class);

        List<KkDeviceJsonDto> kkDeviceJsonDtos = new ArrayList<>();
        kkIntelligentDeviceJsonDtos.forEach(kkIntelligentDeviceJsonDto -> {
            KkDeviceJsonDto kkDeviceJsonDto = new KkDeviceJsonDto();
            kkDeviceJsonDto.setId(kkIntelligentDeviceJsonDto.getId());
            kkDeviceJsonDto.setType(kkIntelligentDeviceJsonDto.getType());
            kkDeviceJsonDto.setLastJoinTime(kkIntelligentDeviceJsonDto.getLastJoinTime());
            if (kkIntelligentDeviceJsonDto.getExtraInfo() != null){
                kkDeviceJsonDto.setMac(kkIntelligentDeviceJsonDto.getExtraInfo().getMac());
                kkDeviceJsonDto.setChannel(kkIntelligentDeviceJsonDto.getExtraInfo().getChannel());
                kkDeviceJsonDto.setGwMac(kkIntelligentDeviceJsonDto.getExtraInfo().getGwMac());
            }
            kkDeviceJsonDtos.add(kkDeviceJsonDto);
        });
        return kkDeviceJsonDtos;
    }

    @Override
    public void openNetwork(String ccuId, String gwId) throws IOException {
        String url = PropertyUtil.getKkApi(KkConstants.OPEN_NETWORK);
        url = url.replace(KkConstants.CCU_ID,ccuId);
        url = url.replace(KkConstants.GW_ID,gwId);

        String response = HttpUtil.requestPut(url);
        HttpUtil.responseProcess(response, KkConstants.OPEN_NETWORK, null);
    }

    @Override
    public void addGwWhiteList(String ccuId, String gwId, String macIp, String productId) throws IOException, DocumentException {
        String url = PropertyUtil.getKkApi(KkConstants.NETWORK_CONFIG);
        url = url.replace(KkConstants.CCU_ID,ccuId);
        url = url.replace(KkConstants.GW_ID,gwId);

        List<Map> mapList = new ArrayList<>();
        Map<String, Object> subOfSubParams = new HashMap<>();
        subOfSubParams.put("mac", macIp);
        subOfSubParams.put("productId", productId);
        mapList.add(subOfSubParams);

        Map<String, Object> subParams = new HashMap<>(16);
        subParams.put("devList", mapList);

        Map<String, Object> params = new HashMap<>(16);
        params.put("action", KkConstants.ADD_GW_WHITELIST);
        params.put("actionArg", subParams);

        String response = HttpUtil.requestPostJson(url, params);
        HttpUtil.responseProcess(response, KkConstants.ADD_GW_WHITELIST, null);
    }
}
