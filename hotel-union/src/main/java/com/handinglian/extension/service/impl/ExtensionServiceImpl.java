package com.handinglian.extension.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.enums.ValidEnum;
import com.handinglian.common.utils.FastJsonUtil;
import com.handinglian.extension.dto.ExtensionDto;
import com.handinglian.extension.entity.Extension;
import com.handinglian.extension.entity.PhoneSetMeal;
import com.handinglian.extension.mapper.ExtensionMapper;
import com.handinglian.extension.service.ExtensionService;
import com.handinglian.extension.service.PhoneSetMealService;
import com.handinglian.yunyi.YunyiApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service("extensionService")
public class ExtensionServiceImpl implements ExtensionService {
    @Autowired
    private ExtensionMapper extensionMapper;
    @Autowired
    private PhoneSetMealService phoneSetMealService;
    @Autowired
    private YunyiApiService yunyiApiService;

    @Override
    @Transactional
    public int createExtension(ExtensionDto extensionDto) throws IOException {
        PhoneSetMeal phoneSetMeal = phoneSetMealService.loadPhoneSetMealByIsDefault();

        Date date = new Date();
        Extension extension = FastJsonUtil.ObjectToObject(extensionDto, Extension.class);
        extension.setCreateTime(date);
        extension.setUpdateTime(date);
        extension.setSetMealId(phoneSetMeal.getPhoneSetMealId());
        int amount = extensionMapper.insertSelective(extension);

        yunyiApiService.addExtension(extension.getExtensionNo(), extension.getRoomNo(), extension.getPasssword(), extension.getRecordState());
        return amount;
    }

    @Override
    public Extension loadExtension(Integer extensionId) {
        return extensionMapper.selectByPrimaryKey(extensionId);
    }

    @Override
    @Transactional
    public int updateExtension(ExtensionDto extensionDto) throws IOException {
        Extension extension = FastJsonUtil.ObjectToObject(extensionDto, Extension.class);

        extension.setUpdateTime(new Date());
        int amount = extensionMapper.updateByPrimaryKeySelective(extension);

        yunyiApiService.updateExtension(extensionDto.getExtensionNo(), extensionDto.getRoomNo(), extensionDto.getPasssword(), extensionDto.getRecordState());
        return amount;
    }

    @Override
    @Transactional
    public int updateExtension(Extension extension) {
        return extensionMapper.updateByPrimaryKeySelective(extension);
    }

    @Override
    @Transactional
    public int deleteExtension(Integer extensionId) throws IOException {
        Extension extension = extensionMapper.selectByPrimaryKey(extensionId);
        extension.setExtensionId(extensionId);
        extension.setValid(ValidEnum.INVALID.getKey());
        int amount = extensionMapper.updateByPrimaryKeySelective(extension);

        yunyiApiService.deleteExtension(extension.getExtensionNo());
        return amount;
    }

    @Override
    public PageInfo<Extension> inquireExtensionPageList(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 1 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;

        PageHelper.startPage(pageIndex, pageSize);
        List<Extension> extensionList = extensionMapper.find();
        PageInfo<Extension> extensionPageInfo =new PageInfo<>(extensionList);
        return extensionPageInfo;
    }

    @Override
    public Extension loadInvalidExtensionByExtensionNo(String extensionNo) {
        return extensionMapper.findInvalidOneByExtensionNo(extensionNo);
    }

    @Override
    public int recoverExtension(Integer updatedValid, String extensionNo) throws IOException {
        Extension extension = extensionMapper.findInvalidOneByExtensionNo(extensionNo);
        extension.setValid(ValidEnum.VALID.getKey());
        int amount = extensionMapper.updateByPrimaryKeySelective(extension);

        yunyiApiService.addExtension(extension.getExtensionNo(), extension.getRoomNo(), extension.getPasssword(), extension.getRecordState());
        return amount;
    }

    @Override
    public Extension loadExtensionByExtensionNo(String extensionNo) {
        return extensionMapper.findOneByExtensionNo(extensionNo);
    }

    @Override
    @Transactional
    public int updateSetMealIdByExtensionNo(Integer updatedSetMealId, String extensionNo) {
        return extensionMapper.updateSetMealIdByExtensionNo(updatedSetMealId, extensionNo);
    }

    @Override
    public int updateSetMealIdBySetMealId(Integer newSetMealId, Integer oldSetMealId) {
        return extensionMapper.updateSetMealIdBySetMealId(newSetMealId, oldSetMealId);
    }
}
