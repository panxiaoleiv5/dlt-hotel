package com.handinglian.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YyBizException extends BizException {
    private static final long serialVersionUID = 1L;

    /**
     * 返回控客数据为空
     */
    public final static YyBizException DATA_IS_NULL = new YyBizException(10012001, "返回云翌数据为空");

    /**
     * 请求控客接口失败
     */
    public final static YyBizException REQUEST_FAILURE = new YyBizException(10012002, "请求云翌接口失败");

    public YyBizException() {
    }

    public YyBizException(String message, Throwable cause){
        super(message, cause);
    }

    public YyBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public YyBizException(int code, String msg) {
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
    public YyBizException newInstance(String msgFormat, Object... args) {
        return new YyBizException(this.code, msgFormat, args);
    }

    public YyBizException print() {
        log.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return new YyBizException(this.code, this.msg);
    }

    public String getErrorInfo() {
        StringBuilder db = new StringBuilder();
        db.append("==>BizException, code:").append(this.code).append(", msg").append(this.msg);
        return db.toString();
    }
}
