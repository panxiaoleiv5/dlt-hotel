package com.handinglian.system.mapper;
import java.util.List;

import com.handinglian.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Role findOneByRoleName(@Param("roleName") String roleName);

    Role findInvalidOneByRoleName(@Param("roleName") String roleName);

    List<Role> find();

}