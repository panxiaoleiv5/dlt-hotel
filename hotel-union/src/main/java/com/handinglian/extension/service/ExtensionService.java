package com.handinglian.extension.service;

import com.github.pagehelper.PageInfo;
import com.handinglian.extension.dto.ExtensionDto;
import com.handinglian.extension.entity.Extension;

import java.io.IOException;

public interface ExtensionService {

    /**
     * 插入分机
     * @param extensionDto
     * @return
     */
    int createExtension(ExtensionDto extensionDto) throws IOException;

    Extension loadExtension(Integer extensionId);

    int updateExtension(ExtensionDto extensionDto) throws IOException;

    int updateExtension(Extension extension);

    int deleteExtension(Integer extensionId) throws IOException;

    PageInfo<Extension> inquireExtensionPageList(Integer pageIndex, Integer pageSize);

    Extension loadInvalidExtensionByExtensionNo(String extensionNo);

    int recoverExtension(Integer updatedValid, String extensionNo) throws IOException;

    Extension loadExtensionByExtensionNo(String extensionNo);

    /**
    * 更换套餐为指定套餐
    * @author pxl
    * @param updatedSetMealId
    * @param extensionNo
    * @return int
    * @date 2019/7/23 16:55
    */
    int updateSetMealIdByExtensionNo(Integer updatedSetMealId, String extensionNo);

    /**
    * 更换分机的默认套餐
    * @author pxl
    * @param newSetMealId
    * @param oldSetMealId
    * @return int
    * @date 2019/7/15 10:33
    */
    int updateSetMealIdBySetMealId(Integer newSetMealId, Integer oldSetMealId);
}
