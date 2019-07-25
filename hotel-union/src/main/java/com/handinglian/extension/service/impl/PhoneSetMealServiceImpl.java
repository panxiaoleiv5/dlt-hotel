package com.handinglian.extension.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.enums.ValidEnum;
import com.handinglian.common.utils.FastJsonUtil;
import com.handinglian.extension.dto.PhoneSetMealDetailDto;
import com.handinglian.extension.dto.PhoneSetMealDto;
import com.handinglian.extension.entity.PhoneSetMeal;
import com.handinglian.extension.entity.PhoneSetMealDetail;
import com.handinglian.extension.enums.SetMealDefaultTypeEnum;
import com.handinglian.extension.mapper.PhoneSetMealMapper;
import com.handinglian.extension.param.SetMealCreateParam;
import com.handinglian.extension.param.SetMealDetailCreateParam;
import com.handinglian.extension.param.SetMealUpdateParam;
import com.handinglian.extension.service.ExtensionService;
import com.handinglian.extension.service.PhoneSetMealDetailService;
import com.handinglian.extension.service.PhoneSetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("phoneSetMealService")
public class PhoneSetMealServiceImpl implements PhoneSetMealService {

    @Autowired
    private PhoneSetMealMapper phoneSetMealMapper;
    @Autowired
    @Lazy
    private ExtensionService extensionService;
    @Autowired
    private PhoneSetMealDetailService phoneSetMealDetailService;

    @Override
    @Transactional
    public int createPhoneSetMeal(SetMealCreateParam setMealCreateParam) {
        PhoneSetMeal oldDefaultSetMeal = loadPhoneSetMealByIsDefault();

        //创建套餐
        Date date = new Date();
        PhoneSetMeal phoneSetMeal = new PhoneSetMeal();
        phoneSetMeal.setSetMealName(setMealCreateParam.getSetMealName());
        Integer isDefault = setMealCreateParam.getIsDefault() == null?0: setMealCreateParam.getIsDefault();
        phoneSetMeal.setIsDefault(isDefault);
        phoneSetMeal.setCreateDate(date);
        phoneSetMeal.setUpdateTime(date);
        phoneSetMeal.setAreaCodeId(setMealCreateParam.getAreaCodeId());
        int amount = phoneSetMealMapper.insertSelective(phoneSetMeal);

        //若新增套餐是默认套餐，则修改分机所属的默认套餐
        if (setMealCreateParam.getIsDefault() != null&&setMealCreateParam.getIsDefault() != 0){
            if (!oldDefaultSetMeal.getPhoneSetMealId().equals(phoneSetMeal.getPhoneSetMealId())){
                oldDefaultSetMeal.setIsDefault(SetMealDefaultTypeEnum.NOT_DEFAULT.getKey());
                updatePhoneSetMeal(oldDefaultSetMeal);
                extensionService.updateSetMealIdBySetMealId(phoneSetMeal.getPhoneSetMealId(), oldDefaultSetMeal.getPhoneSetMealId());
            }
        }

        //修改分机所属套餐
        List<String> extensionNos = setMealCreateParam.getExtensionNos();
        extensionNos.forEach(extensionNo->{
            extensionService.updateSetMealIdByExtensionNo(phoneSetMeal.getPhoneSetMealId(), extensionNo);
        });

        //创建套餐明细
        List<SetMealDetailCreateParam> setMealDetailCreateParams = setMealCreateParam.getSetMealDetailCreateParams();
        setMealDetailCreateParams.forEach(phoneSetMealDetailDto ->{
            phoneSetMealDetailService.createPhoneSetMealDetail(phoneSetMealDetailDto, phoneSetMeal.getPhoneSetMealId());
        });
        return amount;
    }

    @Override
    public PhoneSetMeal loadPhoneSetMealByIsDefault() {
        return phoneSetMealMapper.findDefaultOne();
    }


    @Override
    public PhoneSetMeal loadInvalidSetMealBySetMealName(String setMealName) {
        return phoneSetMealMapper.findInvalidOneBySetMealName(setMealName);
    }

    @Override
    public PhoneSetMealDto loadPhoneSetMealDetail(Integer phoneSetMealId) {
        PhoneSetMeal phoneSetMeal = phoneSetMealMapper.selectByPrimaryKey(phoneSetMealId);
        List<PhoneSetMealDetail> phoneSetMealDetailList = phoneSetMealDetailService.inquirePhoneSetMealDetailList(phoneSetMealId);

        PhoneSetMealDto phoneSetMealDto = FastJsonUtil.ObjectToObject(phoneSetMeal, PhoneSetMealDto.class);
        List<PhoneSetMealDetailDto> phoneSetMealDetailDtos = FastJsonUtil.ListToList(phoneSetMealDetailList, PhoneSetMealDetailDto.class);
        phoneSetMealDto.setPhoneSetMealDetailDtos(phoneSetMealDetailDtos);
        return phoneSetMealDto;
    }

    @Override
    public int updatePhoneSetMeal(SetMealUpdateParam setMealUpdateParam) {
        PhoneSetMeal oldDefaultSetMeal = loadPhoneSetMealByIsDefault();

        PhoneSetMeal phoneSetMeal = FastJsonUtil.ObjectToObject(setMealUpdateParam, PhoneSetMeal.class);
        phoneSetMeal.setUpdateTime(new Date());
        int amount = updatePhoneSetMeal(phoneSetMeal);

        //若更新套餐为默认套餐，则修改分机所属的默认套餐
        if (setMealUpdateParam.getIsDefault() != null&&setMealUpdateParam.getIsDefault() != 0){
            if (!oldDefaultSetMeal.getPhoneSetMealId().equals(phoneSetMeal.getPhoneSetMealId())){
                oldDefaultSetMeal.setIsDefault(SetMealDefaultTypeEnum.NOT_DEFAULT.getKey());
                updatePhoneSetMeal(oldDefaultSetMeal);
                extensionService.updateSetMealIdBySetMealId(phoneSetMeal.getPhoneSetMealId(), oldDefaultSetMeal.getPhoneSetMealId());
            }
        }

        //修改分机所属套餐
        List<String> extensionNos = setMealUpdateParam.getExtensionNos();
        extensionNos.forEach(extensionNo->{
            extensionService.updateSetMealIdByExtensionNo(phoneSetMeal.getPhoneSetMealId(), extensionNo);
        });

        //更新套餐明细
        List<PhoneSetMealDetail> phoneSetMealDetails = FastJsonUtil.ListToList(setMealUpdateParam.getSetMealDetailCreateParams(), PhoneSetMealDetail.class);
        phoneSetMealDetailService.updatePhoneSetMealDetailBatch(phoneSetMealDetails);
        return amount;
    }

    @Override
    public int updatePhoneSetMeal(PhoneSetMeal phoneSetMeal) {
        return phoneSetMealMapper.updateByPrimaryKeySelective(phoneSetMeal);
    }

    @Override
    public int deletePhoneSetMeal(Integer phoneSetMealId) {
        PhoneSetMeal phoneSetMeal = new PhoneSetMeal();
        phoneSetMeal.setPhoneSetMealId(phoneSetMealId);
        phoneSetMeal.setValid(ValidEnum.INVALID.getKey());
        return phoneSetMealMapper.updateByPrimaryKeySelective(phoneSetMeal);
    }

    @Override
    public PageInfo<PhoneSetMealDto> inquirePhoneSetMealPageList(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 1 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;

        PageHelper.startPage(pageIndex, pageSize);
        List<PhoneSetMeal> phoneSetMeals = phoneSetMealMapper.find();
        List<PhoneSetMealDto> phoneSetMealDtos = FastJsonUtil.ListToList(phoneSetMeals, PhoneSetMealDto.class);
        PageInfo<PhoneSetMealDto> phoneSetMealPageInfo =new PageInfo<PhoneSetMealDto>(phoneSetMealDtos);
        return phoneSetMealPageInfo;
    }

    @Override
    @Transactional
    public int recoverPhoneSetMeal(String setMealName) {
        PhoneSetMeal phoneSetMeal = phoneSetMealMapper.findInvalidOneBySetMealName(setMealName);

        phoneSetMeal.setValid(ValidEnum.VALID.getKey());
        return updatePhoneSetMeal(phoneSetMeal);
    }

    @Override
    public PhoneSetMeal loadPhoneSetMeal(Integer phoneSetMealId) {
        return phoneSetMealMapper.selectByPrimaryKey(phoneSetMealId);
    }
}
