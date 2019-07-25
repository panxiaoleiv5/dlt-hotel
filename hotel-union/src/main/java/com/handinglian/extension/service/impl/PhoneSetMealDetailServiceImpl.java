package com.handinglian.extension.service.impl;

import com.handinglian.common.utils.FastJsonUtil;
import com.handinglian.extension.entity.PhoneSetMealDetail;
import com.handinglian.extension.mapper.PhoneSetMealDetailMapper;
import com.handinglian.extension.param.SetMealDetailCreateParam;
import com.handinglian.extension.service.PhoneSetMealDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("phoneSetMealDetailService")
public class PhoneSetMealDetailServiceImpl implements PhoneSetMealDetailService {
    @Autowired
    private PhoneSetMealDetailMapper phoneSetMealDetailMapper;

    @Override
    @Transactional
    public int createPhoneSetMealDetail(SetMealDetailCreateParam setMealDetailCreateParam, Integer setMealId) {
        Date date = new Date();
        PhoneSetMealDetail phoneSetMealDetail = FastJsonUtil.ObjectToObject(setMealDetailCreateParam, PhoneSetMealDetail.class);
        phoneSetMealDetail.setPhoneSetMealId(setMealId);
        phoneSetMealDetail.setCreateTime(date);
        phoneSetMealDetail.setUpdateTime(date);
        return phoneSetMealDetailMapper.insertSelective(phoneSetMealDetail);
    }

    @Override
    @Transactional
    public int createPhoneSetMealDetailBatch(List<PhoneSetMealDetail> list) {
        return phoneSetMealDetailMapper.batchInsert(list);
    }

    @Override
    @Transactional
    public int updatePhoneSetMealDetailBatch(List<PhoneSetMealDetail> list) {
        return phoneSetMealDetailMapper.updateBatch(list);
    }

    @Override
    public List<PhoneSetMealDetail> inquirePhoneSetMealDetailList(Integer phoneSetMealId) {
        return phoneSetMealDetailMapper.findByPhoneSetMealId(phoneSetMealId);
    }
}
