package com.handinglian.extension.controller;

import com.github.pagehelper.PageInfo;
import com.handinglian.common.constants.Constants;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.enums.ValidEnum;
import com.handinglian.common.exception.BizException;
import com.handinglian.common.factory.ResultDataFactory;
import com.handinglian.common.utils.MD5Util;
import com.handinglian.common.utils.StringUtil;
import com.handinglian.extension.entity.Extension;
import com.handinglian.extension.service.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/extension")
public class ExtensionController {
    @Autowired
    private ExtensionService extensionService;

    /**
     * 创建分机
     */
    @PostMapping("/createExtension")
    public ResultData createExtension(String extensionNo, String roomNo, String password){


        Extension extension = extensionService.loadInvalidExtensionByExtensionNo(extensionNo);
        if (extension != null){
            return new ResultData(Constants.EXIST_IN_DELETE, Constants.EXIST_IN_DELETE_CONTENT, extensionNo);
        }

        if (extension != null){
            return new ResultData(Constants.DUPLICATE, Constants.DUPLICATE_CONTENT);
        }

        extension = new Extension();
        Date now = new Date();
        extension.setExtensionNo(extensionNo);
        extension.setRoomNo(roomNo);

        String randomCode = String.valueOf(Math.abs(new Random().nextInt()) % 10000);
        extension.setPasssword(MD5Util.encrypt(password+randomCode));
        extension.setRandomCode(randomCode);
        extension.setCreateTime(now);
        extension.setUpdateTime(now);

        int amount = extensionService.createExtension(extension);

        return ResultDataFactory.generateResultData(amount);

    }

    /**
     * 从删除列表中恢复分机
     */
    @PutMapping("/recoverExtension")
    public ResultData recoverExtension(String extensionNo){
        int amount = extensionService.updateValidByExtensionNo(ValidEnum.VALID.getKey(), extensionNo);

        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 删除分机
     */
    @DeleteMapping("/deleteExtension")
    public ResultData deleteExtension(Integer extensionId){
        Extension extension = new Extension();
        extension.setExtensionId(extensionId);
        extension.setValid(ValidEnum.INVALID.getKey());
        int amount = extensionService.updateExtension(extension);

        return ResultDataFactory.generateResultData(amount);

    }

    /**
     * 更新分机
     */
    @PutMapping("/updateExtension")
    public ResultData updateExtension(Integer extensionId, String extensionNo, String roomNo, String password){
        if (StringUtil.isBlank(password)){
            throw new BizException("密码不得为空!!");
        }

        Extension extension = new Extension();

        extension.setExtensionId(extensionId);
        extension.setRoomNo(roomNo);
        extension.setExtensionNo(extensionNo);
        extension.setUpdateTime(new Date());

        if (StringUtil.isNotBlank(password)){
            String randomCode = String.valueOf(Math.abs(new Random().nextInt()) % 10000);
            extension.setPasssword(MD5Util.encrypt(password+randomCode));
        }

        int amount = extensionService.updateExtension(extension);

        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 获取分机信息
     */
    @GetMapping("/loadExtension")
    public ResultData loadExtension(Integer extensionId){
        Extension extension = extensionService.loadExtension(extensionId);
        return new ResultData(Constants.SUCCESS, Constants.SUCCESS_CONTENT, extension);
    }

    /**
     * 获取分机分页列表
     */
    @GetMapping("/inquireExtensionPageList")
    public ResultData inquireExtensionPageList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        PageInfo<Extension> extensionPageInfo = extensionService.inquireExtensionPageList(pageIndex, pageSize);
        return new ResultData(Constants.SUCCESS, Constants.SUCCESS_CONTENT, extensionPageInfo);
    }
}
