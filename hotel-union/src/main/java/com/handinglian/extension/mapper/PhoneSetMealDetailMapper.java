package com.handinglian.extension.mapper;

import com.handinglian.extension.entity.PhoneSetMealDetail;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PhoneSetMealDetailMapper {
    int deleteByPrimaryKey(Integer phoneSetMealDetailId);

    int insert(PhoneSetMealDetail record);

    int insertOrUpdate(PhoneSetMealDetail record);

    int insertOrUpdateSelective(PhoneSetMealDetail record);

    int insertSelective(PhoneSetMealDetail record);

    PhoneSetMealDetail selectByPrimaryKey(Integer phoneSetMealDetailId);

    int updateByPrimaryKeySelective(PhoneSetMealDetail record);

    int updateByPrimaryKey(PhoneSetMealDetail record);

    int updateBatch(List<PhoneSetMealDetail> list);

    int batchInsert(@Param("list") List<PhoneSetMealDetail> list);

    List<PhoneSetMealDetail> findByPhoneSetMealId(@Param("phoneSetMealId") Integer phoneSetMealId);
}