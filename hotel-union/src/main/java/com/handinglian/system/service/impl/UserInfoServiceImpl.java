package com.handinglian.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.enums.ValidEnum;
import com.handinglian.common.utils.FastJsonUtil;
import com.handinglian.common.utils.MD5Util;
import com.handinglian.system.dto.UserInfoDto;
import com.handinglian.system.entity.Permission;
import com.handinglian.system.entity.UserInfo;
import com.handinglian.system.mapper.UserInfoMapper;
import com.handinglian.system.service.UserInfoService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @Transactional
    public int createUserInfo(UserInfoDto userInfoDto) {
        Date now = new Date();
        UserInfo user = FastJsonUtil.ObjectToObject(userInfoDto, UserInfo.class);

        //产生盐：工号+随机字串
        String salt = user.getJobNum() + new SecureRandomNumberGenerator().nextBytes().toHex();
        String password = generatePassword(salt);

        user.setPassword(password);
        user.setSalt(salt);
        user.setCreateTime(now);
        user.setUpdateTime(now);

        return userInfoMapper.insertSelective(user);
    }

    private String generatePassword(String salt) {
        String password = "1han2345ding6lian";
        String passwordMd5 = MD5Util.encrypt(password);

        SimpleHash hash = new SimpleHash("md5", passwordMd5, salt, 2);
        return hash.toHex();
    }

    @Override
    public UserInfo loadInvalidUserInfo(String jobNum) {
        return userInfoMapper.findInvalidOneByJobNum(jobNum);
    }

    @Override
    @Transactional
    public int recoverUserInfo(String jobNum) {
        UserInfo user = userInfoMapper.findInvalidOneByJobNum(jobNum);
        user.setValid(ValidEnum.VALID.getKey());

        return updateUserInfo(user);
    }

    @Override
    @Transactional
    public int updateUserInfo(UserInfo user) {
        return userInfoMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional
    public int deleteUserInfo(Integer userId) {
        UserInfo user = userInfoMapper.selectByPrimaryKey(userId);
        user.setValid(ValidEnum.INVALID.getKey());
        return userInfoMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public UserInfo loadUserInfo(Integer userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public PageInfo<UserInfo> inquireUserInfoPageList(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 1 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;

        PageHelper.startPage(pageIndex, pageSize);
        return new PageInfo<>(userInfoMapper.find());
    }

    @Override
    public UserInfo loadUserInfoByJobNum(String jobNum) {
        return userInfoMapper.findOneByJobNum(jobNum);
    }

    @Override
    public List<Permission> inquirePermissionList(String jobNum) {
        return userInfoMapper.inquirePermissionList(jobNum);
    }
}
