package com.handinglian.kongke.service;

import com.handinglian.kongke.dto.*;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.List;

public interface KongkeApiService {
    /**
    * 删除非zigbee设备
    * @author pxl
    * @param ccuId 中控主机id
    * @param kkDeviceId 控客设备id
    * @param kkDeviceType 控客设备类型
    * @return void
    * @date 2019/6/11 12:48
    */
    void deleteNotZigbeeDevice(String ccuId, String kkDeviceId, String kkDeviceType) throws IOException, DocumentException;

    /**
    * 删除白名单
    * @author pxl
    * @param ccuId 中控主机id
    * @param gwId 网关id
    * @param macIp 识别码
    * @return void
    * @date 2019/6/11 12:49
    */
    void deleteGwWhiteList(String ccuId, String gwId, String macIp) throws IOException, DocumentException;

    /**
    * 查询网关白名单运行状态
    * @author pxl
    * @param ccuId 中控主机id
    * @param gwId 网关id 
    * @return com.handinglian.kongke.dto.KkWhiteListStateJsonDto
    * @date 2019/6/11 12:50
    */
    KkWhiteListStateJsonDto getGwWorkmode(String ccuId, String gwId) throws IOException, DocumentException;

    /**
    * 设置网关白名单运行状态
    * @author pxl
    * @param ccuId
    * @param gwId 
    * @return void
    * @date 2019/6/11 12:51
    */
    void setGwWorkmode(String ccuId, String gwId) throws IOException, DocumentException;

    /**
    * 绑定中控主机
    * @author pxl
    * @param devQRCode 识别码，既macId
    * @return com.handinglian.kongke.dto.KkCreateCentralHostJsonDto
    * @date 2019/6/11 12:51
    */
    KkCreateCentralHostJsonDto bindingCentralHost(String devQRCode) throws IOException, DocumentException;

    /**
    * 解绑中控主机
    * @author pxl
    * @param ccuId 
    * @return void
    * @date 2019/6/11 12:51
    */
    void unbindingCentralHost(String ccuId) throws IOException, DocumentException;

    /**
    * 获取中控主机基本信息
    * @author pxl
    * @param ccuId 
    * @return com.handinglian.kongke.dto.KkHostBaseInfoJsonDto
    * @date 2019/6/11 12:52
    */
    KkHostBaseInfoJsonDto getHostBaseInfo(String ccuId) throws IOException;

    /**
    * 查询中控主机内节点列表
    * @author pxl
    * @param ccuId 
    * @return java.util.List<com.handinglian.kongke.dto.KkZigbeeDevicesJsonDto>
    * @date 2019/6/11 12:52
    */
    List<KkZigbeeDevicesJsonDto> getNodeList(String ccuId) throws IOException;

    /**
    * 查询中控主机内设备列表
    * @author pxl
    * @param ccuId
    * @return java.util.List<com.handinglian.kongke.dto.KkDeviceJsonDto>
    * @date 2019/6/11 13:56
    */
    List<KkDeviceJsonDto> getDeviceList(String ccuId) throws IOException;

    /**
    * 打开网关组网通道
    * @author pxl
    * @param ccuId
    * @param gwId 
    * @return void
    * @date 2019/6/11 12:52
    */
    void openNetwork(String ccuId, String gwId) throws IOException;

    /**
    * 添加网关白名单列表
    * @author pxl
    * @param ccuId
    * @param gwId
    * @param macIp
    * @param productId 产品id
    * @return void
    * @date 2019/6/11 12:53
    */
    void addGwWhiteList(String ccuId, String gwId, String macIp, String productId) throws IOException, DocumentException;
}
