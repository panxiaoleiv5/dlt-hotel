package com.handinglian.contentunion.controller;

import com.apidoc.annotation.Api;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.factory.ResultDataFactory;
import com.handinglian.contentunion.dto.IntelligentDeviceDetailDto;
import com.handinglian.contentunion.dto.IntelligentDeviceDto;
import com.handinglian.contentunion.dto.IntelligentDeviceReturnDto;
import com.handinglian.contentunion.dto.IntelligentSubDeviceDto;
import com.handinglian.contentunion.entity.IntelligentDevice;
import com.handinglian.contentunion.entity.ProductList;
import com.handinglian.contentunion.service.IntelligentDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api("智能设备")
@Slf4j
@RestController
@RequestMapping("/intelligentDevice")
public class IntelligentDeviceController {

    @Autowired
    private IntelligentDeviceService intelligentDeviceService;

    /**
     * 创建智能设备
     */
    @PostMapping("/createIntelligentDevice")
    public ResultData createIntelligentDevice(@RequestBody IntelligentDeviceDto intelligentDeviceDto) throws IOException, DocumentException {
        IntelligentDevice intelligentDevice = intelligentDeviceService.loadInvalidIntelligentDevice(intelligentDeviceDto.getMacIp());
        if (intelligentDevice != null){
            return ResultDataFactory.generateExistInDeleteResultData();
        }

        int amount = intelligentDeviceService.createIntelligentDevice(intelligentDeviceDto);

        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 从删除列表恢复智能设备
     */
    @PutMapping("/recoverIntelligentDevice")
    public ResultData recoverIntelligentDevice(@RequestBody IntelligentDeviceDto intelligentDeviceDto) throws IOException, DocumentException {
        int amount = intelligentDeviceService.recoverIntelligentDevice(intelligentDeviceDto.getMacIp());
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 删除智能设备
     */
    @DeleteMapping("/deleteIntelligentDevice")
    public ResultData deleteIntelligentDevice(Integer intelligentDeviceId) throws IOException, DocumentException {
        int amount = intelligentDeviceService.deleteIntelligentDevice(intelligentDeviceId);
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 更新智能设备
     */
    @PutMapping("/updateIntelligentDevice")
    public ResultData updateIntelligentDevice(@RequestBody IntelligentDeviceDto intelligentDeviceDto){
        int amount = intelligentDeviceService.updateIntelligentDevice(intelligentDeviceDto.getIntelligentDeviceId(), intelligentDeviceDto.getDeviceAddress(), intelligentDeviceDto.getExtensionId(), intelligentDeviceDto.getCentralHostId());
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 更新智能子设备
     */
    @PutMapping("/updateIntelligentSubDevice")
    public ResultData updateIntelligentSubDevice(@RequestBody IntelligentSubDeviceDto intelligentSubDeviceDto){
        int amount = intelligentDeviceService.updateIntelligentSubDevice(intelligentSubDeviceDto.getIntelligentSubDeviceId(), intelligentSubDeviceDto.getSubProductName(), intelligentSubDeviceDto.getPower());
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 查询智能设备详情
     */
    @GetMapping("/loadIntelligentDeviceDetail")
    public ResultData<IntelligentDeviceDetailDto> loadIntelligentDeviceDetail(Integer intelligentDeviceId){
        IntelligentDeviceDetailDto intelligentDeviceDetailDto = intelligentDeviceService.loadIntelligentDeviceDetail(intelligentDeviceId);
        return ResultDataFactory.generateSuccessResultData(intelligentDeviceDetailDto);
    }

    /**
     * 获取智能设备分页列表
     */
    @GetMapping("/inquireIntelligentDevicePageList")
    public ResultData<IntelligentDeviceReturnDto> inquireIntelligentDevicePageList(Integer pageIndex, Integer pageSize){
        pageIndex = pageIndex == null?1:pageIndex;
        pageSize = pageSize == null?10:pageSize;
        PageInfo pageInfo = intelligentDeviceService.inquireIntelligentDevicePageList(pageIndex, pageSize);
        return ResultDataFactory.generateSuccessResultData(pageInfo);
    }

    /**
     * 获取产品设备列表
     */
    @GetMapping("/inquireProductList")
    public ResultData<ProductList> inquireProductList(){
        List<ProductList> productLists = intelligentDeviceService.inquireProductList();
        return ResultDataFactory.generateSuccessResultData(productLists);
    }
}
