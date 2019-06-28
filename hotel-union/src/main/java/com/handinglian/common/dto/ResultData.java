package com.handinglian.common.dto;

import lombok.Data;

/**
 * @author pxl
 * @description
 * @date 2019/4/12 15:26
 */
@Data
public class ResultData<T> {
    /**
     * 字段码
     */
    private Integer code;

    /**
     * 描述
     */
    private String description;

    /**
     * 返回数据
     */
    private T data;

    public ResultData() {
    }

    public ResultData(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public ResultData(Integer code, String description, T data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }
}
