package com.handinglian.kongke.dto;

/**
 * @description:
 * @author: pxl
 * @create: 2019/6/4 10:25
 */
public class KkWhiteListStateJsonDto {

    /**
     * success : true
     * errorCode : 0
     * data : {"mode":1}
     */

    private boolean success;
    private int errorCode;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mode : 1
         */

        private int mode;

        public int getMode() {
            return mode;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }
    }
}
