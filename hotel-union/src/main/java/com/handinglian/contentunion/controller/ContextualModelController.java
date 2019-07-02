package com.handinglian.contentunion.controller;

import com.apidoc.annotation.Api;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.factory.ResultDataFactory;
import com.handinglian.contentunion.dto.ContextualModelDto;
import com.handinglian.contentunion.entity.ContextualModel;
import com.handinglian.contentunion.service.ContextualModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        int amount = contextualModelService.createContextualModel(contextualModelDto.getModelName());
        return ResultDataFactory.generateResultData(amount);
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
    public ResultData deleteContextualModel(Integer contextualModelId) {
        int amount = contextualModelService.deleteContextualModel(contextualModelId);
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 添加智能物品
     */
    @PostMapping(value = "/addIntelligentArticle")
    public ResultData addIntelligentArticle(@RequestBody ContextualModelDto contextualModelDto) {
        int amount = contextualModelService.addIntelligentArticle(contextualModelDto.getKkDeviceNames(), contextualModelDto.getContextualModelId());
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
    public ResultData<PageInfo> inquireIntelligentArticlePageList(Integer page, Integer pageSize, String name) {
        PageInfo<String> pageInfo = contextualModelService.inquireIntelligentArticlePageList(page, pageSize);
        return ResultDataFactory.generateSuccessResultData(pageInfo);
    }
}
