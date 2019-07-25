package com.handinglian.extension.mapper;

import com.handinglian.extension.entity.Extension;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExtensionMapper {
    int deleteByPrimaryKey(Integer extensionId);

    int insert(Extension record);

    int insertOrUpdate(Extension record);

    int insertOrUpdateSelective(Extension record);

    int insertSelective(Extension record);

    Extension selectByPrimaryKey(Integer extensionId);

    int updateByPrimaryKeySelective(Extension record);

    int updateByPrimaryKey(Extension record);

    int updateBatch(List<Extension> list);

    int batchInsert(@Param("list") List<Extension> list);

    List<Extension> find();

    Extension findInvalidOneByExtensionNo(@Param("extensionNo") String extensionNo);

    Extension findOneByExtensionNo(@Param("extensionNo") String extensionNo);

    int updateValidByExtensionNo(@Param("updatedValid") Integer updatedValid, @Param("extensionNo") String extensionNo);

    int updateSetMealIdByExtensionNo(@Param("updatedSetMealId")Integer updatedSetMealId,@Param("extensionNo")String extensionNo);

    int updateSetMealIdBySetMealId(@Param("newSetMealId")Integer newSetMealId,@Param("oldSetMealId")Integer oldSetMealId);
}