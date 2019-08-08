package com.handinglian.contentunion.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.constants.KkConstants;
import com.handinglian.common.enums.ValidEnum;
import com.handinglian.common.exception.BizException;
import com.handinglian.common.utils.*;
import com.handinglian.contentunion.dto.IntelligentDeviceDetailDto;
import com.handinglian.contentunion.dto.IntelligentDeviceDto;
import com.handinglian.contentunion.dto.IntelligentDeviceReturnDto;
import com.handinglian.contentunion.entity.*;
import com.handinglian.contentunion.mapper.*;
import com.handinglian.contentunion.service.IntelligentDeviceService;
import com.handinglian.kongke.dto.KkWhiteListStateJsonDto;
import com.handinglian.kongke.service.KongkeApiService;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service("intelligentDeviceService")
public class IntelligentDeviceServiceImpl implements IntelligentDeviceService {
    @Autowired
    private IntelligentDeviceMapper intelligentDeviceMapper;
    @Autowired
    private IntelligentExtensionRelationMapper intelligentExtensionRelationMapper;
    @Autowired
    private IntelligentSubDeviceMapper intelligentSubDeviceMapper;
    @Autowired
    private CentralHostMapper centralHostMapper;
    @Autowired
    private GatewayMapper gatewayMapper;
    @Autowired
    private KongkeApiService kongkeApiService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createIntelligentDevice(IntelligentDeviceDto intelligentDeviceDto) throws IOException, DocumentException {
        String macIp = GeneralUtil.processMacIp(intelligentDeviceDto.getMacIp());

        //智能设备存入数据库
        IntelligentDevice intelligentDevice = FastJsonUtil.ObjectToObject(intelligentDeviceDto, IntelligentDevice.class);
        intelligentDevice.setMacIp(macIp);
        intelligentDevice.setCreateTime(new Date());
        intelligentDevice.setUpdateTime(new Date());
        intelligentDevice.setValid(1);

        int amount = intelligentDeviceMapper.insertSelective(intelligentDevice);

        String[] extensionIds = StringUtil.isBlank(intelligentDeviceDto.getExtensionId())?new String[]{}:intelligentDeviceDto.getExtensionId().split(",");
        Date now = new Date();
        for (String extensionid : extensionIds){
            IntelligentExtensionRelation intelligentExtensionRelation = new IntelligentExtensionRelation();
            intelligentExtensionRelation.setIntelligentDeviceId(intelligentDevice.getIntelligentDeviceId());
            intelligentExtensionRelation.setExtensionId(Integer.valueOf(extensionid));
            intelligentExtensionRelation.setCreateTime(now);
            intelligentExtensionRelationMapper.insert(intelligentExtensionRelation);
        }

        CentralHost centralHost = centralHostMapper.selectByPrimaryKey(intelligentDevice.getCentralHostId());
        Gateway gateway = gatewayMapper.findOneByCentralHostId(intelligentDevice.getCentralHostId());

        //检查是否是白名单模式
        KkWhiteListStateJsonDto kkWhiteListStateJsonDto = kongkeApiService.getGwWorkmode(centralHost.getKkHostId(), gateway.getGwId().toString());


        //不是白名单，则将名单模式置为白名单
        int mode = kkWhiteListStateJsonDto.getData().getMode();
        if (mode != 1){
            kongkeApiService.setGwWorkmode(centralHost.getKkHostId(), gateway.getGwId().toString());
        }

        //添加白名单
        kongkeApiService.addGwWhiteList(centralHost.getKkHostId(), gateway.getGwId().toString(), macIp, intelligentDevice.getProductId());

        return amount;
    }

    @Override
    public int recoverIntelligentDevice(String macIp) throws IOException, DocumentException {
        IntelligentDevice intelligentDevice = intelligentDeviceMapper.findInvalidOneByMacIp(macIp);
        intelligentDevice.setValid(ValidEnum.VALID.getKey());
        intelligentDevice.setUpdateTime(new Date());
        int amount = intelligentDeviceMapper.updateByPrimaryKeySelective(intelligentDevice);

        CentralHost centralHost = centralHostMapper.selectByPrimaryKey(intelligentDevice.getCentralHostId());
        Gateway gateway = gatewayMapper.findOneByCentralHostId(intelligentDevice.getCentralHostId());

        //检查是否是白名单模式
        KkWhiteListStateJsonDto kkWhiteListStateJsonDto = kongkeApiService.getGwWorkmode(centralHost.getKkHostId(), gateway.getGwId().toString());


        //不是白名单，则将名单模式置为白名单
        int mode = kkWhiteListStateJsonDto.getData().getMode();
        if (mode != 1){
            kongkeApiService.setGwWorkmode(centralHost.getKkHostId(), gateway.getGwId().toString());
        }

        //添加白名单
        kongkeApiService.addGwWhiteList(centralHost.getKkHostId(), gateway.getGwId().toString(), intelligentDevice.getMacIp(), intelligentDevice.getProductId());

        return amount;
    }

    @Override
    @Transactional
    public int updateIntelligentDevice(Integer intelligentDeviceId, String deviceAddress, String extensionId, Integer centralHostId) {
        IntelligentDevice intelligentDevice = new IntelligentDevice();
        intelligentDevice.setIntelligentDeviceId(intelligentDeviceId);
        intelligentDevice.setDeviceAddress(deviceAddress);
        intelligentDevice.setCentralHostId(centralHostId);
        intelligentDevice.setUpdateTime(new Date());

        int amount = intelligentDeviceMapper.updateByPrimaryKeySelective(intelligentDevice);

        List<Integer> extensionIdDatabase = intelligentExtensionRelationMapper.findExtensionIdByIntelligentDeviceId(intelligentDevice.getIntelligentDeviceId());
        List<Integer> extensionIdParam = StringUtil.stringToIntegerList(extensionId);

        //比较获取应删除的列表和应增加的列表
        List<Integer> deleteList = GeneralUtil.getMissingList(extensionIdParam, extensionIdDatabase);
        List<Integer> addList = GeneralUtil.getAddList(extensionIdParam, extensionIdDatabase);

        if (deleteList.size() > 0){
            intelligentExtensionRelationMapper.deleteByintelligentDeviceIdAndExtensionIdIn(intelligentDevice.getIntelligentDeviceId(), deleteList);
        }

        Date now = new Date();
        for (Integer extensionIdInteger : addList){
            IntelligentExtensionRelation intelligentExtensionRelation = new IntelligentExtensionRelation();
            intelligentExtensionRelation.setIntelligentDeviceId(intelligentDevice.getIntelligentDeviceId());
            intelligentExtensionRelation.setExtensionId(extensionIdInteger);
            intelligentExtensionRelation.setCreateTime(now);
            intelligentExtensionRelationMapper.insertSelective(intelligentExtensionRelation);
        }

        return amount;
    }

    @Override
    public int updateIntelligentSubDevice(Integer intelligentSubDeviceId, String deviceName, Integer power) {
        IntelligentSubDevice intelligentSubDevice = new IntelligentSubDevice();
        intelligentSubDevice.setIntelligentSubDeviceId(intelligentSubDeviceId);
        intelligentSubDevice.setSubDeviceName(deviceName);
        intelligentSubDevice.setPower(power);

        return intelligentSubDeviceMapper.updateByPrimaryKeySelective(intelligentSubDevice);
    }

    @Override
    @Transactional
    public int deleteIntelligentDevice(Integer intelligentDeviceId) throws IOException, DocumentException {
        List<IntelligentSubDevice> intelligentSubDeviceList = intelligentSubDeviceMapper.findByIntelligentDeviceId(intelligentDeviceId);

        IntelligentDevice intelligentDevice = intelligentDeviceMapper.selectByPrimaryKey(intelligentDeviceId);
        String kkType = intelligentDevice.getKkType();
        intelligentDevice.setIntelligentDeviceId(intelligentDeviceId);
        intelligentDevice.setGwMac(null);
        intelligentDevice.setKkType(null);
        intelligentDevice.setValid(ValidEnum.INVALID.getKey());
        int amount = intelligentDeviceMapper.updateByPrimaryKey(intelligentDevice);

        intelligentSubDeviceMapper.deleteByIntelligentDeviceId(intelligentDeviceId);

        CentralHost centralHost = centralHostMapper.selectByPrimaryKey(intelligentDevice.getCentralHostId());
        Gateway gateway = gatewayMapper.findOneByCentralHostId(intelligentDevice.getCentralHostId());
        //删除设备
        kongkeApiService.deleteNotZigbeeDevice(centralHost.getKkHostId(), intelligentSubDeviceList.get(0).getKkDeviceId().toString(), kkType);

        try {
            //删除白名单
            kongkeApiService.deleteGwWhiteList(centralHost.getKkHostId(), gateway.getGwId().toString(), intelligentDevice.getMacIp());
        } catch (BizException e){
            kongkeApiService.openNetwork(centralHost.getKkHostId(), gateway.getGwId().toString());
            throw e;
        }
        return amount;
    }

    @Override
    public IntelligentDevice loadIntelligentDevice(Integer intelligentDeviceId) {
        return intelligentDeviceMapper.selectByPrimaryKey(intelligentDeviceId);
    }

    @Override
    public IntelligentDevice loadInvalidIntelligentDevice(String macIp) {
        return intelligentDeviceMapper.findInvalidOneByMacIp(macIp);
    }

    @Override
    public PageInfo<IntelligentDeviceReturnDto> inquireIntelligentDevicePageList(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<IntelligentDeviceReturnDto> intelligentDeviceReturnDtos = intelligentDeviceMapper.inquireIntelligentExtensionPageList();

        return new PageInfo<>(intelligentDeviceReturnDtos);
    }

    @Override
    public IntelligentDeviceDetailDto loadIntelligentDeviceDetail(Integer intelligentDeviceId) {
        IntelligentDevice intelligentDevice = intelligentDeviceMapper.selectByPrimaryKey(intelligentDeviceId);
        IntelligentDeviceDetailDto intelligentDeviceDetailDto = FastJsonUtil.ObjectToObject(intelligentDevice, IntelligentDeviceDetailDto.class);

        List<IntelligentSubDevice> intelligentSubDeviceList = intelligentSubDeviceMapper.findByIntelligentDeviceId(intelligentDeviceId);
        intelligentDeviceDetailDto.setIntelligentSubDeviceList(intelligentSubDeviceList);

        return intelligentDeviceDetailDto;
    }

    @Override
    public List<ProductList> inquireProductList() {
        return intelligentDeviceMapper.inquireProductList();
    }
}
