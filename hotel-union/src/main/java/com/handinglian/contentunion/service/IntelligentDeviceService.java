package com.handinglian.contentunion.service;

import com.github.pagehelper.PageInfo;
import com.handinglian.contentunion.dto.IntelligentDeviceDetailDto;
import com.handinglian.contentunion.dto.IntelligentDeviceDto;
import com.handinglian.contentunion.entity.IntelligentDevice;
import com.handinglian.contentunion.entity.IntelligentSubDevice;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.List;

public interface IntelligentDeviceService {
    int createIntelligentDevice(IntelligentDeviceDto intelligentDeviceDto) throws IOException, DocumentException;

    /**
    * 从删除列表中恢复数据
    * @author pxl
    * @param intelligentDeviceId
    * @return int
    * @date 2019/6/11 15:19
    */
    int recoverIntelligentDevice(Integer intelligentDeviceId) throws IOException, DocumentException;

    int updateIntelligentDevice(Integer intelligentDeviceId, String deviceAddress, String extensionId, Integer centralHostId);

    int updateIntelligentSubDevice(Integer intelligentSubDeviceId, String deviceName, Integer power);

    int deleteIntelligentDevice(Integer intelligentDeviceId) throws IOException, DocumentException;

    IntelligentDevice loadIntelligentDevice(Integer intelligentDeviceId);

    /**
    * 查询被删除的智能设备
    * @author pxl
    * @param macIp
    * @return com.handinglian.contentunion.entity.IntelligentDevice
    * @date 2019/6/5 15:59
    */
    IntelligentDevice loadInvalidIntelligentDevice(String macIp);

    /**
    * 获取智能设备的分页列表
    * @author pxl
    * @param pageIndex 页数
    * @param pageSize 每页条数
    * @return com.github.pagehelper.PageInfo<com.handinglian.contentunion.entity.IntelligentDevice>
    * @date 2019/6/5 15:59
    */
    PageInfo<IntelligentDevice> inquireIntelligentDevicePageList(Integer pageIndex, Integer pageSize);

    /**
    * 获取智能设备明细
    * @author pxl
    * @param intelligentDeviceId
    * @return com.handinglian.contentunion.dto.IntelligentDeviceDetailDto
    * @date 2019/6/5 16:00
    */
    IntelligentDeviceDetailDto loadIntelligentDeviceDetail(Integer intelligentDeviceId);
}
