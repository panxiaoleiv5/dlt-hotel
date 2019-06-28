package com.handinglian.extension.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.handinglian.extension.entity.Extension;
import com.handinglian.extension.mapper.ExtensionMapper;
import com.handinglian.extension.service.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("extensionService")
public class ExtensionServiceImpl implements ExtensionService {
    @Autowired
    private ExtensionMapper extensionMapper;

    @Override
    @Transactional
    public int createExtension(Extension extension) {
        return extensionMapper.insertSelective(extension);
    }

    @Override
    public Extension loadExtension(Integer extensionId) {
        return extensionMapper.selectByPrimaryKey(extensionId);
    }

    @Override
    @Transactional
    public int updateExtension(Extension extension) {
        return extensionMapper.updateByPrimaryKeySelective(extension);
    }

    @Override
    @Transactional
    public int deleteExtension(Integer extensionId) {
        return extensionMapper.deleteByPrimaryKey(extensionId);
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
    public int updateValidByExtensionNo(Integer updatedValid, String extensionNo) {
        return extensionMapper.updateValidByExtensionNo(updatedValid, extensionNo);
    }

    @Override
    public Extension loadExtensionByExtensionNo(String extensionNo) {
        return extensionMapper.findOneByExtensionNo(extensionNo);
    }
}
