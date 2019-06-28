package com.handinglian.contentunion.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.constants.Constants;
import com.handinglian.common.enums.ValidEnum;
import com.handinglian.common.exception.KkBizException;
import com.handinglian.common.utils.FastJsonUtil;
import com.handinglian.common.utils.StringUtil;
import com.handinglian.contentunion.entity.CentralHost;
import com.handinglian.contentunion.entity.Gateway;
import com.handinglian.contentunion.entity.IntelligentDevice;
import com.handinglian.contentunion.entity.IntelligentSubDevice;
import com.handinglian.contentunion.mapper.CentralHostMapper;
import com.handinglian.contentunion.mapper.GatewayMapper;
import com.handinglian.contentunion.mapper.IntelligentDeviceMapper;
import com.handinglian.contentunion.mapper.IntelligentSubDeviceMapper;
import com.handinglian.contentunion.service.CentralHostService;
import com.handinglian.kongke.dto.*;
import com.handinglian.kongke.service.KongkeApiService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 中控主机
 */
@Slf4j
@Service("centralHostService")
public class CentralHostServiceImpl implements CentralHostService {
    @Autowired
    private CentralHostMapper centralHostMapper;
    @Autowired
    private GatewayMapper gatewayMapper;
    @Autowired
    private IntelligentDeviceMapper intelligentDeviceMapper;
    @Autowired
    private IntelligentSubDeviceMapper intelligentSubDeviceMapper;
    @Autowired
    private KongkeApiService kongkeApiService;

    @Override
    @Transactional
    public int createCentralHost(String hostAddress, String macIp) throws IOException, DocumentException {
        //请求绑定中控主机
        KkCreateCentralHostJsonDto kkCreateCentralHostJsonDto = kongkeApiService.bindingCentralHost(macIp);

        Date now = new Date();
        CentralHost centralHost = new CentralHost();
        centralHost.setHostAddress(hostAddress);
        centralHost.setMacIp(macIp);
        centralHost.setCreateTime(now);
        centralHost.setUpdateTime(now);
        centralHost.setKkHostId(kkCreateCentralHostJsonDto.getId());
        centralHost.setProductId(kkCreateCentralHostJsonDto.getProductId());
        centralHost.setDeviceId(kkCreateCentralHostJsonDto.getDeviceId());

        return centralHostMapper.insertSelective(centralHost);
    }

    @Override
    @Transactional
    public int activateCentralHost(Integer centralHostId) throws IOException {
        CentralHost centralHost = centralHostMapper.selectByPrimaryKey(centralHostId);

        if (StringUtil.isNotBlank(centralHost.getLocalIp())){
            throw new KkBizException(Constants.ERROR, "中控主机已激活,请勿重复激活");
        }

        KkHostBaseInfoJsonDto kkHostBaseInfoJsonDto = kongkeApiService.getHostBaseInfo(centralHost.getKkHostId());

        centralHost.setCurVersion(kkHostBaseInfoJsonDto.getCurVersion());
        centralHost.setDownloadVersion(kkHostBaseInfoJsonDto.getDownloadVersion());
        centralHost.setLocalIp(kkHostBaseInfoJsonDto.getLocalIp());
        int amount = centralHostMapper.updateByPrimaryKeySelective(centralHost);

        List<KkHostBaseInfoJsonDto.GateWayBean> gateWayBeanList = kkHostBaseInfoJsonDto.getGwList();
        for (KkHostBaseInfoJsonDto.GateWayBean gateWayBean : gateWayBeanList){
            Gateway gateway = FastJsonUtil.ObjectToObject(gateWayBean, Gateway.class);
            gateway.setCentralHostId(centralHostId);
            gatewayMapper.insertSelective(gateway);
        }
        return amount;
    }

    @Override
    @Transactional
    public int deleteCentralHost(Integer centralHostId) throws IOException, DocumentException {
        CentralHost centralHost = centralHostMapper.selectByPrimaryKey(centralHostId);

        kongkeApiService.unbindingCentralHost(centralHost.getKkHostId());

        centralHost.setValid(ValidEnum.INVALID.getKey());
        return centralHostMapper.updateByPrimaryKeySelective(centralHost);
    }

    @Override
    public CentralHost loadCentralHost(Integer centralHostId) {
        return centralHostMapper.selectByPrimaryKey(centralHostId);
    }

    @Override
    @Transactional
    public int updateCentralHost(CentralHost centralHost) {
        return centralHostMapper.updateByPrimaryKeySelective(centralHost);
    }

    @Override
    @Transactional
    public int recoverCentralHost(String macIp) throws IOException, DocumentException {
        CentralHost centralHost = centralHostMapper.findOneByMacIp(macIp);

        //请求绑定中控主机
        kongkeApiService.bindingCentralHost(centralHost.getMacIp());
        centralHost.setValid(ValidEnum.VALID.getKey());
        return updateCentralHost(centralHost);
    }

    @Override
    public CentralHost loadInvalidCentralHost(String macIp) {
        return centralHostMapper.findInvalidOneByMacIp(macIp);
    }

    @Override
    public PageInfo<CentralHost> inquireCentralHostPageList(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 1 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;

        PageHelper.startPage(pageIndex, pageSize);
        List<CentralHost> centralHostList = centralHostMapper.find();
        return new PageInfo(centralHostList);
    }

    @Override
    public void uniteNetwork(Integer centralHostId) throws IOException, DocumentException {
        CentralHost centralHost = centralHostMapper.selectByPrimaryKey(centralHostId);
        Gateway gateway = gatewayMapper.findOneByCentralHostId(centralHostId);
        //打开网关通道
        kongkeApiService.openNetwork(centralHost.getKkHostId(), gateway.getGwId().toString());

        List<IntelligentDevice> intelligentDevices = intelligentDeviceMapper.findByCentralHostId(centralHostId);
        //筛选出未更新过的智能设备
        intelligentDevices =  intelligentDevices.stream().filter(intelligentDevice -> StringUtil.isBlank(intelligentDevice.getGwMac())).collect(Collectors.toList());Collectors.toList();

        //请求中控主机内节点列表
        List<KkZigbeeDevicesJsonDto> kkZigbeeDevicesJsonDtos = kongkeApiService.getNodeList(centralHost.getKkHostId());

        intelligentDevices.forEach(intelligentDevice -> {
            //智能设备与节点进行筛选匹配
            Optional<KkZigbeeDevicesJsonDto> optional = kkZigbeeDevicesJsonDtos.stream().filter(zigbeeDevicesJsonDto ->zigbeeDevicesJsonDto.getMac().equals(intelligentDevice.getMacIp())).findFirst();
            if (optional.isPresent()){
                KkZigbeeDevicesJsonDto zigbeeDevicesJsonDto = optional.get();
                //智能设备字段进行更新
                intelligentDeviceMapper.updateGwMacAndKkTypeByMacIp(zigbeeDevicesJsonDto.getGwMac(), zigbeeDevicesJsonDto.getChannelDevices().get(0).getType(), intelligentDevice.getMacIp());

                List<KkChannelDevicesJsonDto> channelDevicesJsonDtos = zigbeeDevicesJsonDto.getChannelDevices();
                //智能子设备插入
                channelDevicesJsonDtos.forEach(channelDevicesJsonDto -> {
                    IntelligentSubDevice intelligentSubDevice = new IntelligentSubDevice();
                    intelligentSubDevice.setIntelligentDeviceId(intelligentDevice.getIntelligentDeviceId());
                    intelligentSubDevice.setChannel(channelDevicesJsonDto.getChannel());
                    intelligentSubDevice.setKkDeviceId(channelDevicesJsonDto.getId());
                    intelligentSubDevice.setCreateTime(new Date());
                    intelligentSubDevice.setUpdateTime(new Date());
                    intelligentSubDeviceMapper.insertSelective(intelligentSubDevice);
                });
            }

        });

    }

}
