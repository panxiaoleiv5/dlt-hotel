package com.handinglian.common.constants;

/**
 * @author pxl
 * @description
 * @date 2019/4/12 15:33
 */
public class Constants {
    //状态码
    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 500;
    public static final Integer DUPLICATE = 501;
    public static final Integer EXIST_IN_DELETE = 502;
    public static final Integer NOT_EXIST = 503;

    //异常内容
    public static final String SUCCESS_CONTENT = "请求成功!";
    public static final String ERROR_CONTENT = "请求失败!";
    public static final String DUPLICATE_CONTENT = "该数据已经存在!";
    public static final String EXIST_IN_DELETE_CONTENT = "该数据已经存在于删除列表中，是否恢复!";
    public static final String NOT_EXIST_CONTENT = "该数据不存在!";
}
