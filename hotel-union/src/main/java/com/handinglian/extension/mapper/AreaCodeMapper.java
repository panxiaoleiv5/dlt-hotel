package com.handinglian.extension.mapper;

import com.handinglian.extension.entity.AreaCode;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AreaCodeMapper {
    int deleteByPrimaryKey(Integer areaCodeId);

    int insert(AreaCode record);

    int insertOrUpdate(AreaCode record);

    int insertOrUpdateSelective(AreaCode record);

    int insertSelective(AreaCode record);

    AreaCode selectByPrimaryKey(Integer areaCodeId);

    int updateByPrimaryKeySelective(AreaCode record);

    int updateByPrimaryKey(AreaCode record);

    int updateBatch(List<AreaCode> list);

    int batchInsert(@Param("list") List<AreaCode> list);

    List<String> findAreaCode();

}