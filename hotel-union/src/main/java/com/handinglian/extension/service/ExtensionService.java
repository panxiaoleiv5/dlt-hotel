package com.handinglian.extension.service;

import com.github.pagehelper.PageInfo;
import com.handinglian.extension.entity.Extension;

public interface ExtensionService {

    /**
     * 插入分机
     * @param extension
     * @return
     */
    int createExtension(Extension extension);

    Extension loadExtension(Integer extensionId);

    int updateExtension(Extension extension);

    int deleteExtension(Integer extensionId);

    PageInfo<Extension> inquireExtensionPageList(Integer pageIndex, Integer pageSize);

    Extension loadInvalidExtensionByExtensionNo(String extensionNo);

    int updateValidByExtensionNo(Integer updatedValid, String extensionNo);

    Extension loadExtensionByExtensionNo(String extensionNo);

}
