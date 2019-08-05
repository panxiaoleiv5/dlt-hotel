package com.handinglian.contentunion.controller;

import com.apidoc.annotation.Api;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.factory.ResultDataFactory;
import com.handinglian.common.utils.FastJsonUtil;
import com.handinglian.common.utils.StringUtil;
import com.handinglian.contentunion.dto.ContextualDeviceDto;
import com.handinglian.contentunion.dto.ContextualModelDetailDto;
import com.handinglian.contentunion.dto.ContextualModelDto;
import com.handinglian.contentunion.entity.ContextualModel;
import com.handinglian.contentunion.service.ContextualModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api("情景模式")
@Slf4j
@RestController
@RequestMapping("/contextualModel")
public class ContextualModelController {

    @Autowired
    private ContextualModelService contextualModelService;
    /**
     * 创建情景模式
     */
    @PostMapping(value = "/createContextualModel")
    public ResultData createCentralHost(@RequestBody ContextualModelDto contextualModelDto) {
        int id = contextualModelService.createContextualModel(contextualModelDto.getModelName());
        return ResultDataFactory.generateSuccessResultData(id);
    }

    /**
     * 查询情景模式
     */
    @GetMapping(value = "/inquireContextualModelPageList")
    public ResultData<PageInfo<ContextualModel>> inquireContextualModelPageList(Integer page, Integer pageSize) {
        PageInfo<ContextualModel> contextualModelPageInfo = contextualModelService.inquireContextualModelPageList(page, pageSize);
        return ResultDataFactory.generateSuccessResultData(contextualModelPageInfo);
    }

    /**
     * 修改情景模式名称
     */
    @PutMapping(value = "/updateContextualModelName")
    public ResultData updateContextualModelName(@RequestBody ContextualModelDto contextualModelDto) {
        int amount = contextualModelService.updateContextualModelName(contextualModelDto.getContextualModelId(), contextualModelDto.getModelName());
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 删除情景模式
     */
    @DeleteMapping(value = "/deleteContextualModel")
    public ResultData deleteContextualModel(String contextualModelIdStr) {
        List<Integer> contextualModelIds = StringUtil.stringToIntegerList(contextualModelIdStr);
        int amount = contextualModelService.deleteContextualModelBatch(contextualModelIds);
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 添加或删除智能物品
     */
    @PostMapping(value = "/addOrDeleteIntelligentArticle")
    public ResultData addOrDeleteIntelligentArticle(@RequestBody ContextualModelDto contextualModelDto) {
        int amount = contextualModelService.addOrDeleteIntelligentArticle(contextualModelDto.getKkDeviceNames(), contextualModelDto.getContextualModelId());
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 删除智能物品
     */
    @DeleteMapping(value = "/deleteIntelligentArticle")
    public ResultData deleteIntelligentArticle(String kkDeviceNameStr, Integer contextualModelId) {
        int amount = contextualModelService.deleteIntelligentArticle(kkDeviceNameStr, contextualModelId);
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 获取智能物品列表
     */
    @GetMapping(value = "/inquireIntelligentArticlePageList")
    public ResultData<PageInfo> inquireIntelligentArticleList(Integer page, Integer pageSize) {
        PageInfo<String> pageInfo = contextualModelService.inquireIntelligentArticlePageList(page, pageSize);
        return ResultDataFactory.generateSuccessResultData(pageInfo);
    }

    /**
     * 查询模式选中的智能物品列表
     */
    @GetMapping(value = "/inquireSelectedIntelligentArticlePageList")
    public ResultData<PageInfo> inquireSelectedIntelligentArticlePageList(Integer contextualModelId, Integer page, Integer pageSize) {
        PageInfo<String> pageInfo = contextualModelService.inquireIntelligentArticlePageList(page, pageSize);
        //获取被选中的设备名称
        List<String> deviceNames = contextualModelService.inquireSelectedIntelligentArticleList(contextualModelId);
        //按分页获取所有设备名称
        List<String> deviceNameAll = pageInfo.getList();

        List<ContextualDeviceDto> contextualDeviceDtos = new ArrayList<>();
        for (String deviceName: deviceNameAll){
            ContextualDeviceDto contextualDeviceDto = new ContextualDeviceDto();
            contextualDeviceDto.setDeviceName(deviceName);
            contextualDeviceDto.setSelected(false);
            contextualDeviceDtos.add(contextualDeviceDto);
        }
        contextualDeviceDtos.forEach(contextualDeviceDto -> {
            if (deviceNames.contains(contextualDeviceDto.getDeviceName())){
                contextualDeviceDto.setSelected(true);
            }
        });

        PageInfo<ContextualDeviceDto> contextualDeviceDtoPageInfo = FastJsonUtil.ObjectToObject(pageInfo, PageInfo.class);
        contextualDeviceDtoPageInfo.setList(contextualDeviceDtos);
        return ResultDataFactory.generateSuccessResultData(contextualDeviceDtoPageInfo);
    }

    /**
     * 获取情景模式详情
     */
    @GetMapping(value = "/loadContextualModelDetail")
    public ResultData<ContextualModelDetailDto> loadContextualModelDetail(Integer contextualModelId) {
        ContextualModelDetailDto contextualModelDetailDto = contextualModelService.loadContextualModelDetail(contextualModelId);
        return ResultDataFactory.generateSuccessResultData(contextualModelDetailDto);
    }
}
