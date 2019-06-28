package com.handinglian.contentunion.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.utils.GeneralUtil;
import com.handinglian.contentunion.entity.ContextualModel;
import com.handinglian.contentunion.mapper.ContextualModelMapper;
import com.handinglian.contentunion.mapper.IntelligentSubDeviceMapper;
import com.handinglian.contentunion.service.ContextualModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        return contextualModelMapper.insertSelective(contextualModel);
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
    public int deleteContextualModel(Integer contextualModelId) {
        return contextualModelMapper.deleteByPrimaryKey(contextualModelId);
    }

    @Override
    public int addIntelligentArticle(String kkDeviceNames, Integer contextualModelId) {
        ContextualModel contextualModel = contextualModelMapper.selectByPrimaryKey(contextualModelId);
        contextualModel.setContextualModelId(contextualModelId);
        contextualModel.setSubDeviceName(kkDeviceNames);
        return contextualModelMapper.updateByPrimaryKey(contextualModel);
    }

    @Override
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
}
