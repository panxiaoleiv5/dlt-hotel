package com.handinglian.contentunion.service;

import com.github.pagehelper.PageInfo;
import com.handinglian.contentunion.entity.ContextualModel;

import java.util.List;

public interface ContextualModelService {
    int createContextualModel(String modelName);

    PageInfo<ContextualModel> inquireContextualModelPageList(Integer page, Integer pageSize);

    int updateContextualModelName(Integer contextualModelId, String modelName);

    int deleteContextualModelBatch(List<Integer> contextualModelIds);

    int addIntelligentArticle(String kkDeviceNames, Integer contextualModelId);

    int deleteIntelligentArticle(String kkDeviceNameStr, Integer contextualModelId);

    PageInfo<String> inquireIntelligentArticlePageList(Integer page, Integer pageSize);
}
