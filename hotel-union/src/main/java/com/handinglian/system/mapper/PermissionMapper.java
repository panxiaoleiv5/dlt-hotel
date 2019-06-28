package com.handinglian.system.mapper;
import com.handinglian.system.dto.PermissionDto;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.handinglian.system.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<PermissionDto> findByMenuId(@Param("menuId")Integer menuId);
}