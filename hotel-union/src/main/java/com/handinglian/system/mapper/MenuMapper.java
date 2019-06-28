package com.handinglian.system.mapper;

import com.handinglian.system.dto.MenuPermissionDto;
import com.handinglian.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<MenuPermissionDto> inquireLevelMenuList(Integer level);
}