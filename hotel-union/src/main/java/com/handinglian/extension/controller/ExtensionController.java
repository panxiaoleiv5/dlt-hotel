package com.handinglian.extension.controller;

import com.apidoc.annotation.Api;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.constants.Constants;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.enums.ValidEnum;
import com.handinglian.common.exception.BizException;
import com.handinglian.common.factory.ResultDataFactory;
import com.handinglian.common.utils.IpUtil;
import com.handinglian.common.utils.MD5Util;
import com.handinglian.common.utils.StringUtil;
import com.handinglian.extension.dto.ExtensionDto;
import com.handinglian.extension.entity.Extension;
import com.handinglian.extension.service.ExtensionService;
import com.handinglian.yunyi.YunyiApiService;
import com.handinglian.yunyi.dto.TestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Api("分机")
@RestController
@RequestMapping("/extension")
public class ExtensionController {
    @Autowired
    private ExtensionService extensionService;
    @Autowired
    private YunyiApiService yunyiApiService;

    /**
     * 创建分机
     */
    @PostMapping("/createExtension")
    public ResultData createExtension(@RequestBody ExtensionDto extensionDto, HttpServletRequest request) throws IOException {
        Extension extension = extensionService.loadInvalidExtensionByExtensionNo(extensionDto.getExtensionNo());
        if (extension != null){
            return new ResultData(Constants.EXIST_IN_DELETE, Constants.EXIST_IN_DELETE_CONTENT, extensionDto.getExtensionNo());
        }

        String ip = IpUtil.getIpAddr(request);
        extensionDto.setRegisterIp(ip);

        int amount = extensionService.createExtension(extensionDto);

        return ResultDataFactory.generateResultData(amount);

    }

    /**
     * 从删除列表中恢复分机
     */
    @PutMapping("/recoverExtension")
    public ResultData recoverExtension(@RequestBody ExtensionDto extensionDto) throws IOException {
        int amount = extensionService.recoverExtension(ValidEnum.VALID.getKey(), extensionDto.getExtensionNo());

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
    public ResultData updateExtension(@RequestBody ExtensionDto extensionDto) throws IOException {
        int amount = extensionService.updateExtension(extensionDto);

        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 获取分机信息
     */
    @GetMapping("/loadExtension")
    public ResultData loadExtension(Integer extensionId) throws IOException {
        yunyiApiService.getExtension("801");
        Extension extension = extensionService.loadExtension(extensionId);
        return new ResultData(Constants.SUCCESS, Constants.SUCCESS_CONTENT, extension);
    }

    /**
     * 获取分机信息
     */
    @GetMapping("/loadYunyiExtension")
    public ResultData loadYunyiExtension(Integer extensionId) throws IOException {
        String str = yunyiApiService.getTestExtension("801");
        System.out.println(str);
        return new ResultData(Constants.SUCCESS, Constants.SUCCESS_CONTENT, str);
    }

    /**
     * 获取分机分页列表
     */
    @GetMapping("/inquireExtensionPageList")
    public ResultData<Extension> inquireExtensionPageList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        PageInfo<Extension> extensionPageInfo = extensionService.inquireExtensionPageList(pageIndex, pageSize);
        return new ResultData(Constants.SUCCESS, Constants.SUCCESS_CONTENT, extensionPageInfo);
    }
}
