package com.handinglian.contentunion.service;

import com.github.pagehelper.PageInfo;
import com.handinglian.contentunion.dto.ContextualModelDetailDto;
import com.handinglian.contentunion.entity.ContextualModel;

import java.util.List;

public interface ContextualModelService {
    int createContextualModel(String modelName);

    PageInfo<ContextualModel> inquireContextualModelPageList(Integer page, Integer pageSize);

    int updateContextualModelName(Integer contextualModelId, String modelName);

    int deleteContextualModelBatch(List<Integer> contextualModelIds);

    int addOrDeleteIntelligentArticle(String kkDeviceNames, Integer contextualModelId);

    int deleteIntelligentArticle(String kkDeviceNameStr, Integer contextualModelId);

    /**
    * 获取智能物品列表
    * @author pxl
    * @param page
    * @param pageSize
    * @return com.github.pagehelper.PageInfo<java.lang.String>
    * @date 2019/7/29 11:57
    */
    PageInfo<String> inquireIntelligentArticlePageList(Integer page, Integer pageSize);

    /**
    * 查询模式选中的智能物品列表
    * @author pxl
    * @param contextualModelId
    * @return java.util.List<java.lang.String>
    * @date 2019/7/29 11:58
    */
    List<String> inquireSelectedIntelligentArticleList(Integer contextualModelId);

    ContextualModelDetailDto loadContextualModelDetail(Integer contextualModelId);
}
