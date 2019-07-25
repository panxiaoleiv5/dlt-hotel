package com.handinglian.extension.service;

import com.handinglian.extension.entity.PhoneSetMealDetail;
import com.handinglian.extension.param.SetMealDetailCreateParam;

import java.util.List;

public interface PhoneSetMealDetailService {
    /**
     * 创建套餐通话明细
     * @author pxl
     * @param setMealDetailCreateParam
     * @return int
     * @date 2019/7/15 10:46
     */
    int createPhoneSetMealDetail(SetMealDetailCreateParam setMealDetailCreateParam, Integer setMealId);

    /**
     * 批量创建套餐明细
     * @author pxl
     * @param list
     * @return int
     * @date 2019/7/15 11:48
     */
    int createPhoneSetMealDetailBatch(List<PhoneSetMealDetail> list);

    /**
    * 批量更新套餐明细
    * @author pxl
    * @param list
    * @return int
    * @date 2019/7/15 15:09
    */
    int updatePhoneSetMealDetailBatch(List<PhoneSetMealDetail> list);

    List<PhoneSetMealDetail> inquirePhoneSetMealDetailList(Integer phoneSetMealId);
}
