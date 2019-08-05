package com.handinglian.contentunion.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.utils.FastJsonUtil;
import com.handinglian.common.utils.GeneralUtil;
import com.handinglian.common.utils.StringUtil;
import com.handinglian.contentunion.dto.ContextualModelDetailDto;
import com.handinglian.contentunion.entity.ContextualModel;
import com.handinglian.contentunion.mapper.ContextualModelMapper;
import com.handinglian.contentunion.mapper.IntelligentSubDeviceMapper;
import com.handinglian.contentunion.service.ContextualModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("contextualModelService")
 public class ContextualModelServiceImpl implements ContextualModelService {
    @Autowired
    private ContextualModelMapper contextualModelMapper;
    @Autowired
    private IntelligentSubDeviceMapper intelligentSubDeviceMapper;

    @Override
    @Transactional
    public int createContextualModel(String modelName) {
        Date now = new Date();
        ContextualModel contextualModel = new ContextualModel();
        contextualModel.setModelName(modelName);
        contextualModel.setCreateTime(now);
        contextualModel.setUpdateTime(now);
        contextualModelMapper.insertSelective(contextualModel);

        return contextualModel.getContextualModelId();
    }

    @Override
    public PageInfo<ContextualModel> inquireContextualModelPageList(Integer page, Integer pageSize) {
        page = page == null?1:page;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(contextualModelMapper.find());
    }

    @Override
    @Transactional
    public int updateContextualModelName(Integer contextualModelId, String modelName) {
        ContextualModel contextualModel = new ContextualModel();
        contextualModel.setContextualModelId(contextualModelId);
        contextualModel.setModelName(modelName);
        contextualModel.setUpdateTime(new Date());

        return contextualModelMapper.updateByPrimaryKeySelective(contextualModel);
    }

    @Override
    @Transactional
    public int deleteContextualModelBatch(List<Integer> contextualModelIds) {
        return contextualModelMapper.deleteByContextualModelIdIn(contextualModelIds);
    }

    @Override
    @Transactional
    public int addOrDeleteIntelligentArticle(String kkDeviceNames, Integer contextualModelId) {
        ContextualModel contextualModel = contextualModelMapper.selectByPrimaryKey(contextualModelId);
        contextualModel.setContextualModelId(contextualModelId);
        contextualModel.setSubDeviceName(kkDeviceNames);
        return contextualModelMapper.updateByPrimaryKey(contextualModel);
    }

    @Override
    @Transactional
    public int deleteIntelligentArticle(String kkDeviceNameStr, Integer contextualModelId) {
        ContextualModel contextualModel = contextualModelMapper.selectByPrimaryKey(contextualModelId);
        String subDEviceNameStr = contextualModel.getSubDeviceName();
        String[] kkDeviceNames = kkDeviceNameStr.split(",");
        for (String kkDeviceName : kkDeviceNames){
            int index = kkDeviceNameStr.indexOf(kkDeviceName+",");
            if (index != -1){
                subDEviceNameStr = subDEviceNameStr.replace(kkDeviceName+",","");
            } else {
                subDEviceNameStr = subDEviceNameStr.replace(","+kkDeviceName,"");
            }
        }
        contextualModel.setSubDeviceName(subDEviceNameStr);
        return contextualModelMapper.updateByPrimaryKeySelective(contextualModel);
    }

    @Override
    public PageInfo<String> inquireIntelligentArticlePageList(Integer page, Integer pageSize) {
        page = page == null?1:page;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(intelligentSubDeviceMapper.inquireSubDeviceNameGroupBySubDeviceName());
    }

    @Override
    public List<String> inquireSelectedIntelligentArticleList(Integer contextualModelId) {
        String deviceNameStr = contextualModelMapper.findOneSubDeviceNameByContextualModelId(contextualModelId);
        String[] deviceNames = deviceNameStr == null?new String[0]:deviceNameStr.split(",");
        return new ArrayList<String>(Arrays.asList(deviceNames));
    }

    @Override
    public ContextualModelDetailDto loadContextualModelDetail(Integer contextualModelId) {
        ContextualModel contextualModel = contextualModelMapper.selectByPrimaryKey(contextualModelId);
        return FastJsonUtil.ObjectToObject(contextualModel, ContextualModelDetailDto.class);
    }
}
