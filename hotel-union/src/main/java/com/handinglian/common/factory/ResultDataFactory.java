package com.handinglian.common.factory;

import com.handinglian.common.constants.Constants;
import com.handinglian.common.dto.ResultData;

public class ResultDataFactory {

    public static ResultData generateResultData(Integer amount){
        if (amount > 0){
            return new ResultData(Constants.SUCCESS, Constants.SUCCESS_CONTENT, amount);
        } else {
            return new ResultData(Constants.ERROR, Constants.ERROR_CONTENT);
        }
    }

    public static <T> ResultData generateSuccessResultData(T data){
        return new ResultData(Constants.SUCCESS, Constants.SUCCESS_CONTENT, data);
    }

    public static <T> ResultData generateExistInDeleteResultData(){
        return new ResultData(Constants.EXIST_IN_DELETE, Constants.EXIST_IN_DELETE_CONTENT);
    }
}
