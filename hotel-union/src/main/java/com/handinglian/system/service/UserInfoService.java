package com.handinglian.system.service;

import com.github.pagehelper.PageInfo;
import com.handinglian.system.entity.Permission;
import com.handinglian.system.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    int createUserInfo(Integer departmentId, String userName, String position, String jobNum, String mobilePhoneNum, String email);

    /**
     * 查询被删除的用户
     * @author pxl
     * @param jobNum
     * @return com.handinglian.contentunion.entity.CentralHost
     * @date 2019/6/3 10:49
     */
    UserInfo loadInvalidUserInfo(String jobNum);

    /**
     * 恢复删除的用户
     * @author pxl
     * @param userName
     * @return int
     * @date 2019/6/3 17:56
     */
    int recoverUserInfo(String userName);

    int updateUserInfo(UserInfo user);

    int deleteUserInfo(Integer userId);

    UserInfo loadUserInfo(Integer userId);

    /**
     * 分页查询用户列表
     * @author pxl
     * @param pageIndex
     * @param pageSize
     * @return com.github.pagehelper.PageInfo<com.handinglian.contentunion.entity.CentralHost>
     * @date 2019/6/3 10:48
     */
    PageInfo<UserInfo> inquireUserInfoPageList(Integer pageIndex, Integer pageSize);

    UserInfo loadUserInfoByJobNum(String jobNum);

    /**
    * 查询用户拥有的权限
    * @author pxl
    * @param jobNum
    * @return java.util.List<com.handinglian.system.entity.Permission>
    * @date 2019/6/25 16:51
    */
    List<Permission> inquirePermissionList(String jobNum);
}
