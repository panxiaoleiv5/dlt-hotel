package com.handinglian.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KkBizException extends BizException {
    private static final long serialVersionUID = 1L;

    /**
     * 返回控客数据为空
     */
    public final static KkBizException DATA_IS_NULL = new KkBizException(10011001, "返回控客数据为空");

    /**
     * 请求控客接口失败
     */
    public final static KkBizException REQUEST_FAILURE = new KkBizException(10011002, "请求控客接口失败");

    public KkBizException() {
    }

    public KkBizException(String message, Throwable cause){
        super(message, cause);
    }

    public KkBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public KkBizException(int code, String msg) {
        super(code, msg);
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    @Override
    public KkBizException newInstance(String msgFormat, Object... args) {
        return new KkBizException(this.code, msgFormat, args);
    }

    public KkBizException print() {
        log.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return new KkBizException(this.code, this.msg);
    }

    public String getErrorInfo() {
        StringBuilder db = new StringBuilder();
        db.append("==>BizException, code:").append(this.code).append(", msg").append(this.msg);
        return db.toString();
    }
}
