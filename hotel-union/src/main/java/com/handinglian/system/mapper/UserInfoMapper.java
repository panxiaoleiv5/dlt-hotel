package com.handinglian.system.mapper;

import com.handinglian.system.entity.Permission;
import com.handinglian.system.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> find();

    UserInfo findOneByJobNum(@Param("jobNum")String jobNum);

    UserInfo findInvalidOneByJobNum(@Param("jobNum")String jobNum);

    List<Permission> inquirePermissionList(String jobNum);
}