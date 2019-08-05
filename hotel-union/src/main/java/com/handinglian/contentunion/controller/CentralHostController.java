package com.handinglian.contentunion.controller;

import com.apidoc.annotation.Api;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.factory.ResultDataFactory;
import com.handinglian.common.service.RedisService;
import com.handinglian.contentunion.dto.CentralHostDto;
import com.handinglian.contentunion.entity.CentralHost;
import com.handinglian.contentunion.entity.IntelligentDevice;
import com.handinglian.contentunion.service.CentralHostService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api("中控主机")
@Slf4j
@RestController
@RequestMapping("/centralHost")
public class CentralHostController {

    @Autowired
    private CentralHostService centralHostService;

    /**
     * 创建中控主机
     */
    @PostMapping(value = "/createCentralHost")
    public ResultData createCentralHost(@RequestBody CentralHostDto centralHostDto) throws IOException, DocumentException {
        CentralHost centralHost = centralHostService.loadInvalidCentralHost(centralHostDto.getMacIp());
        if (centralHost != null){
            return ResultDataFactory.generateExistInDeleteResultData();
        } else {
            int amount = centralHostService.createCentralHost(centralHostDto.getHostAddress(), centralHostDto.getMacIp(), centralHostDto.getCentralHostName());
            return ResultDataFactory.generateResultData(amount);
        }
    }

    /**
    * 激活中控主机
    */
    @PutMapping(value = "/activateCentralHost")
    public ResultData activateCentralHost(@RequestBody CentralHostDto centralHostDto) throws IOException, DocumentException {
        int amount = centralHostService.activateCentralHost(centralHostDto.getCentralHostId());
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 从删除列表中恢复中控主机
     */
    @PutMapping(value = "/recoverCentralHost")
    public ResultData recoverCentralHost(@RequestBody CentralHostDto centralHostDto) throws IOException, DocumentException {
        int amount = centralHostService.recoverCentralHost(centralHostDto.getMacIp());
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 删除中控主机
     */
    @DeleteMapping(value = "/deleteCentralHost")
    public ResultData deleteCentralHost(Integer centralHostId) throws IOException, DocumentException {
        int amount = centralHostService.deleteCentralHost(centralHostId);
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 更新中控主机
     */
    @PutMapping(value = "/updateCentralHost")
    public ResultData updateCentralHost(@RequestBody CentralHostDto centralHostDto) {
        CentralHost centralHost = new CentralHost();
        centralHost.setCentralHostId(centralHostDto.getCentralHostId());
        centralHost.setHostAddress(centralHostDto.getHostAddress());
        centralHost.setCentralHostName(centralHostDto.getCentralHostName());
        centralHost.setUpdateTime(new Date());

        int amount = centralHostService.updateCentralHost(centralHost);
        return ResultDataFactory.generateResultData(amount);
    }


    /**
     * 查询中控主机
     */
    @GetMapping(value = "/loadCentralHost")
    public ResultData<CentralHost> loadCentralHost(Integer centralHostId) {
        CentralHost centralHost = centralHostService.loadCentralHost(centralHostId);
        return ResultDataFactory.generateSuccessResultData(centralHost);
    }

    /**
     * 获取中控主机分页列表
     */
    @GetMapping("/inquireCentralHostPageList")
    public ResultData<PageInfo<CentralHost>> inquireCentralHostPageList(Integer pageIndex, Integer pageSize) {
        PageInfo<CentralHost> centralHostPageInfo = centralHostService.inquireCentralHostPageList(pageIndex, pageSize);
        return ResultDataFactory.generateSuccessResultData(centralHostPageInfo);
    }

    /**
     * 中控主机组网
     */
    @PutMapping("/uniteNetwork")
    public ResultData uniteNetwork(@RequestBody CentralHostDto centralHostDto) throws IOException, DocumentException {
        centralHostService.uniteNetwork(centralHostDto.getCentralHostId());
        return ResultDataFactory.generateSuccessResultData(null);
    }

}
