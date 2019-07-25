package com.handinglian.extension.controller;

import com.apidoc.annotation.Api;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.constants.Constants;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.factory.ResultDataFactory;
import com.handinglian.extension.dto.PhoneSetMealDto;
import com.handinglian.extension.entity.PhoneSetMeal;
import com.handinglian.extension.param.SetMealCreateParam;
import com.handinglian.extension.param.SetMealRecoverParam;
import com.handinglian.extension.param.SetMealUpdateParam;
import com.handinglian.extension.service.PhoneSetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("通话套餐")
@RequestMapping("/setmeal")
@RestController
public class SetMealController {
    @Autowired
    private PhoneSetMealService phoneSetMealService;

    /**
     * 创建套餐
     */
    @PostMapping("/createSetMeal")
    public ResultData createSetMeal(@RequestBody SetMealCreateParam setMealCreateParam){
        PhoneSetMeal phoneSetMeal = phoneSetMealService.loadInvalidSetMealBySetMealName(setMealCreateParam.getSetMealName());
        if (phoneSetMeal != null){
            return new ResultData(Constants.EXIST_IN_DELETE, Constants.EXIST_IN_DELETE_CONTENT, phoneSetMeal.getSetMealName());
        }
        int amount = phoneSetMealService.createPhoneSetMeal(setMealCreateParam);
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 从删除列表中恢复套餐
     */
    @PutMapping("/recoverSetMeal")
    public ResultData recoverSetMeal(@RequestBody SetMealRecoverParam setMealRecoverParam){
        int amount = phoneSetMealService.recoverPhoneSetMeal(setMealRecoverParam.getSetMealName());
        return ResultDataFactory.generateResultData(amount);
    }
    
    /**
     * 删除套餐
     */
    @DeleteMapping(value = "/deletePhoneSetMeal")
    public ResultData deletePhoneSetMeal(Integer phoneSetMealId) {
        int amount = phoneSetMealService.deletePhoneSetMeal(phoneSetMealId);
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 更新套餐
     */
    @PutMapping(value = "/updatePhoneSetMeal")
    public ResultData updatePhoneSetMeal(@RequestBody SetMealUpdateParam setMealUpdateParam) {
        int amount = phoneSetMealService.updatePhoneSetMeal(setMealUpdateParam);
        return ResultDataFactory.generateResultData(amount);
    }


    /**
     * 查询套餐
     */
    @GetMapping(value = "/loadPhoneSetMeal")
    public ResultData<PhoneSetMealDto> loadPhoneSetMeal(Integer phoneSetMealId) {
        PhoneSetMealDto phoneSetMealDto = phoneSetMealService.loadPhoneSetMealDetail(phoneSetMealId);
        return ResultDataFactory.generateSuccessResultData(phoneSetMealDto);
    }

    /**
     * 获取套餐分页列表
     */
    @GetMapping("/inquirePhoneSetMealPageList")
    public ResultData<PageInfo<PhoneSetMealDto>> inquirePhoneSetMealPageList(Integer pageIndex, Integer pageSize) {
        PageInfo<PhoneSetMealDto> phoneSetMealPageInfo = phoneSetMealService.inquirePhoneSetMealPageList(pageIndex, pageSize);
        return ResultDataFactory.generateSuccessResultData(phoneSetMealPageInfo);
    }

}
