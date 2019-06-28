package com.handinglian.common.advice;

import com.handinglian.common.constants.Constants;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author pxl
 * @description
 * @date 2019/4/12 16:27
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = {Exception.class
    })
    public ResultData handleException(
            Throwable throwable) {
        log.error("异常处理",throwable);
        ResultData resultData = new ResultData(Constants.ERROR, throwable.getMessage());
        return resultData;
    }

    @ExceptionHandler(value = {DuplicateKeyException.class
    })
    public ResultData handleDuplicateKeyException(
            Throwable throwable) {
        log.error("异常处理",throwable);
        ResultData resultData = new ResultData(Constants.DUPLICATE, Constants.DUPLICATE_CONTENT);
        return resultData;
    }

    @ExceptionHandler(value = {BizException.class
    })
    public ResultData handleBizException(
            Throwable throwable) {
        log.error("异常处理",throwable);
        BizException bizException = (BizException) throwable;
        ResultData resultData = new ResultData(bizException.getCode(), bizException.getMsg());
        return resultData;
    }
}
