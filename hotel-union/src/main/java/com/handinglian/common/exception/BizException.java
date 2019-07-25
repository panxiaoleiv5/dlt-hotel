package com.handinglian.common.exception;

/**
 * @author pxl
 * @description
 * @date 2018/10/30 14:34
 *  业务异常基类，所有业务异常都必须继承于此异常
 *  定义异常时，需要先确定异常所属模块。例如：添加数据库插入报错 可以定义为 [90040001] 前四位数为系统模块编号，后4位为错误代码 ,唯一
 *  控客异常  1001
 *  共有异常  9004
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 数据库操作,insert返回0
     */
    public static final BizException DB_INSERT_RESULT_ERROR = new BizException(90040001, "数据库操作,insert出错");

    /**
     * 数据库操作,update返回0
     */
    public static final BizException DB_UPDATE_RESULT_ERROR_ = new BizException(90040002, "数据库操作,update出错");

    /**
     * 数据库操作,selectOne返回null
     */
    public static final BizException DB_SELECTONE_IS_NULL = new BizException(90040003, "数据库操作,selectOne返回null");

    /**
     * 数据库操作,list返回null
     */
    public static final BizException DB_LIST_IS_NULL = new BizException(90040004, "数据库操作,list返回null");

    /**
     * 会话超时
     */
    public static final BizException SESSION_IS_OUT_TIME = new BizException(90040006, "会话超时");

    /**
     * 获取序列出错
     */
    public static final BizException DB_GET_SEQ_NEXT_VALUE_ERROR = new BizException(90040007, "获取序列出错");

    /**
     * 连接超时
     */
    public final static BizException CONNECTION_TIMED_OUT = new YyBizException(90040008, "连接超时");

    /**
     * 异常信息
     */
    protected String msg;

    /**
     * 具体异常码
     */
    protected int code;

    public BizException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public BizException() {
        super();
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    public BizException newInstance(String msgFormat, Object... args) {
        return new BizException(this.code, msgFormat, args);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message) {
        super(message);
    }


}
