package com.handinglian.contentunion.mapper;

import com.handinglian.contentunion.entity.ContextualModel;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContextualModelMapper {
    int deleteByPrimaryKey(Integer contextualModelId);

    int insert(ContextualModel record);

    int insertOrUpdate(ContextualModel record);

    int insertOrUpdateSelective(ContextualModel record);

    int insertSelective(ContextualModel record);

    ContextualModel selectByPrimaryKey(Integer contextualModelId);

    int updateByPrimaryKeySelective(ContextualModel record);

    int updateByPrimaryKey(ContextualModel record);

    int updateBatch(List<ContextualModel> list);

    int batchInsert(@Param("list") List<ContextualModel> list);

    List<ContextualModel> find();
}