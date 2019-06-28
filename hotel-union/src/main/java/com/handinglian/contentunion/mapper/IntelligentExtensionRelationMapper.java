package com.handinglian.contentunion.mapper;

import com.handinglian.contentunion.entity.IntelligentExtensionRelation;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IntelligentExtensionRelationMapper {
    int deleteByPrimaryKey(Integer intelligentExtensionRelationId);

    int insert(IntelligentExtensionRelation record);

    int insertOrUpdate(IntelligentExtensionRelation record);

    int insertOrUpdateSelective(IntelligentExtensionRelation record);

    int insertSelective(IntelligentExtensionRelation record);

    IntelligentExtensionRelation selectByPrimaryKey(Integer intelligentExtensionRelationId);

    int updateByPrimaryKeySelective(IntelligentExtensionRelation record);

    int updateByPrimaryKey(IntelligentExtensionRelation record);

    int updateBatch(List<IntelligentExtensionRelation> list);

    int batchInsert(@Param("list") List<IntelligentExtensionRelation> list);

    List<Integer> findExtensionIdByIntelligentDeviceId(@Param("intelligentDeviceId") Integer intelligentDeviceId);

    int deleteByintelligentDeviceIdAndExtensionIdIn(@Param("intelligentDeviceId") Integer intelligentDeviceId, @Param("extensionIdList") List<Integer> extensionIdList);
}