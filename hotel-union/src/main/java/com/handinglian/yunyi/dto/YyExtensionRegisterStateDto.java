package com.handinglian.yunyi.dto;

import java.util.List;

public class YyExtensionRegisterStateDto {

    /**
     * message : [{"exten":"801","status":"1"}]
     * statuscode : 000000
     */

    private String statuscode;
    private List<MessageBean> message;

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * exten : 801
         * status : 1
         */

        private String exten;
        private String status;

        public String getExten() {
            return exten;
        }

        public void setExten(String exten) {
            this.exten = exten;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
