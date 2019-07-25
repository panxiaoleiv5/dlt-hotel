package com.handinglian.yunyi.dto;

import java.util.List;

public class YyCallRecordDto {

    /**
     * statuscode : 000000
     * message : [{"RecordStatus":"0","CallerNO":"10000","calledNO":"100000","starTime":"2016-06-05 12:30:23","durationTime":"50","recordFile":""}]
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
         * RecordStatus : 0
         * CallerNO : 10000
         * calledNO : 100000
         * starTime : 2016-06-05 12:30:23
         * durationTime : 50
         * recordFile :
         */

        private String RecordStatus;
        private String CallerNO;
        private String calledNO;
        private String starTime;
        private String durationTime;
        private String recordFile;
        private String status;
        private String reqid;

        public String getRecordStatus() {
            return RecordStatus;
        }

        public void setRecordStatus(String RecordStatus) {
            this.RecordStatus = RecordStatus;
        }

        public String getCallerNO() {
            return CallerNO;
        }

        public void setCallerNO(String CallerNO) {
            this.CallerNO = CallerNO;
        }

        public String getCalledNO() {
            return calledNO;
        }

        public void setCalledNO(String calledNO) {
            this.calledNO = calledNO;
        }

        public String getStarTime() {
            return starTime;
        }

        public void setStarTime(String starTime) {
            this.starTime = starTime;
        }

        public String getDurationTime() {
            return durationTime;
        }

        public void setDurationTime(String durationTime) {
            this.durationTime = durationTime;
        }

        public String getRecordFile() {
            return recordFile;
        }

        public void setRecordFile(String recordFile) {
            this.recordFile = recordFile;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReqid() {
            return reqid;
        }

        public void setReqid(String reqid) {
            this.reqid = reqid;
        }
    }
}
