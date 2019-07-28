package com.handinglian.extension.mapper;

import com.handinglian.extension.entity.PhoneSetMeal;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PhoneSetMealMapper {
    int deleteByPrimaryKey(Integer phoneSetMealId);

    int insert(PhoneSetMeal record);

    int insertOrUpdate(PhoneSetMeal record);

    int insertOrUpdateSelective(PhoneSetMeal record);

    int insertSelective(PhoneSetMeal record);

    PhoneSetMeal selectByPrimaryKey(Integer phoneSetMealId);

    int updateByPrimaryKeySelective(PhoneSetMeal record);

    int updateByPrimaryKey(PhoneSetMeal record);

    int updateBatch(List<PhoneSetMeal> list);

    int batchInsert(@Param("list") List<PhoneSetMeal> list);

    PhoneSetMeal findDefaultOne();

    PhoneSetMeal findInvalidOneBySetMealName(@Param("setMealName") String setMealName);

    PhoneSetMeal findOneBySetMealName(@Param("setMealName") String setMealName);

    List<PhoneSetMeal> find();
}