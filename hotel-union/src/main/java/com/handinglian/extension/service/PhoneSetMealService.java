package com.handinglian.extension.service;

import com.github.pagehelper.PageInfo;
import com.handinglian.extension.dto.PhoneSetMealDto;
import com.handinglian.extension.entity.PhoneSetMeal;
import com.handinglian.extension.entity.PhoneSetMealDetail;
import com.handinglian.extension.param.SetMealCreateParam;
import com.handinglian.extension.param.SetMealDetailCreateParam;
import com.handinglian.extension.param.SetMealUpdateParam;

import java.util.List;

public interface PhoneSetMealService {
    int createPhoneSetMeal(SetMealCreateParam setMealCreateParam);

    PhoneSetMeal loadPhoneSetMealByIsDefault();

    /**
    * 查询无效的套餐
    * @author pxl
    * @param setMealName
    * @return com.handinglian.extension.entity.PhoneSetMeal
    * @date 2019/7/15 10:55
    */
    PhoneSetMeal loadInvalidSetMealBySetMealName(String setMealName);

    PhoneSetMealDto loadPhoneSetMealDetail(Integer phoneSetMealId);

    int updatePhoneSetMeal(SetMealUpdateParam setMealUpdateParam);

    int updatePhoneSetMeal(PhoneSetMeal phoneSetMeal);

    int deletePhoneSetMeal(Integer phoneSetMealId);

    /**
    * 分页查询套餐列表
    * @author pxl
    * @param pageIndex
    * @param pageSize
    * @return com.github.pagehelper.PageInfo<com.handinglian.extension.entity.PhoneSetMeal>
    * @date 2019/7/15 11:26
    */
    PageInfo<PhoneSetMealDto> inquirePhoneSetMealPageList(Integer pageIndex, Integer pageSize);

    /**
     * 恢复删除的套餐
     * @author pxl
     * @param setMealName 套餐名称
     * @return int
     * @date 2019/6/3 17:56
     */
    int recoverPhoneSetMeal(String setMealName);

    PhoneSetMeal loadPhoneSetMeal(Integer phoneSetMealId);
}
