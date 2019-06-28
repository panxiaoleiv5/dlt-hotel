package com.handinglian.system.controller;

import com.apidoc.annotation.Api;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.factory.ResultDataFactory;
import com.handinglian.system.entity.UserInfo;
import com.handinglian.system.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api("用户")
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 创建用户
     */
    @PostMapping("/createUserInfo")
    public ResultData createUserInfo(Integer departmentId, String userName, String position, String jobNum, String mobilePhoneNum, String email){
        UserInfo userInfo = userInfoService.loadInvalidUserInfo(jobNum);
        if (userInfo != null){
            return ResultDataFactory.generateExistInDeleteResultData();
        } else {
            int amount = userInfoService.createUserInfo(departmentId, userName, position, jobNum, mobilePhoneNum, email);
            return ResultDataFactory.generateResultData(amount);
        }
    }

    /**
     * 从删除列表中恢复用户
     */
    @PutMapping(value = "/recoverUserInfo")
    public ResultData recoverUserInfo(String jobNum) {
        int amount = userInfoService.recoverUserInfo(jobNum);
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 删除用户
     */
    @DeleteMapping(value = "/deleteUserInfo")
    public ResultData deleteUserInfo(Integer userId) {
        int amount = userInfoService.deleteUserInfo(userId);
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 更新用户
     */
    @PutMapping(value = "/updateUserInfo")
    public ResultData updateUserInfo(Integer userId, Integer departmentId, String userName, String position, String jobNum, String mobilePhoneNum, String email) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setDepartmentId(departmentId);
        userInfo.setUserName(userName);
        userInfo.setPosition(position);
        userInfo.setJobNum(jobNum);
        userInfo.setMobilePhoneNum(mobilePhoneNum);
        userInfo.setEamil(email);
        userInfo.setUpdateTime(new Date());

        int amount = userInfoService.updateUserInfo(userInfo);
        return ResultDataFactory.generateResultData(amount);
    }


    /**
     * 查询用户
     */
    @GetMapping(value = "/loadUserInfo")
    public ResultData<UserInfo> loadUserInfo(Integer userId) {
        UserInfo userInfo = userInfoService.loadUserInfo(userId);
        return ResultDataFactory.generateSuccessResultData(userInfo);
    }

    /**
     * 获取用户列表
     */
    @GetMapping("/inquireUserInfoList")
    public ResultData<PageInfo<UserInfo>> inquireUserInfoPageList(Integer pageIndex, Integer pageSize) {
        PageInfo<UserInfo> userInfoPageInfo = userInfoService.inquireUserInfoPageList(pageIndex, pageSize);
        return ResultDataFactory.generateSuccessResultData(userInfoPageInfo);
    }
}
