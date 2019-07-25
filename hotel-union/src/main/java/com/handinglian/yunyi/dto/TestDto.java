package com.handinglian.yunyi.dto;

import java.util.List;

public class TestDto {

    /**
     * string : {"message":[{"deptName":"A组","displayName":"801","employeeName":"801","exten":"801","extenPassword":"admin246","mobile":"","needRecord":"1","passWord":"d41d8cd98f00b204e9800998ecf8427e","registerip":"","status":"1","transferall":"","transferbusy":"","transfernoanswer":"","transferoffline":"","userName":""}],"statuscode":"000000"}
     */

    private StringBean string;

    public StringBean getString() {
        return string;
    }

    public void setString(StringBean string) {
        this.string = string;
    }

    public static class StringBean {
        /**
         * message : [{"deptName":"A组","displayName":"801","employeeName":"801","exten":"801","extenPassword":"admin246","mobile":"","needRecord":"1","passWord":"d41d8cd98f00b204e9800998ecf8427e","registerip":"","status":"1","transferall":"","transferbusy":"","transfernoanswer":"","transferoffline":"","userName":""}]
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
             * deptName : A组
             * displayName : 801
             * employeeName : 801
             * exten : 801
             * extenPassword : admin246
             * mobile :
             * needRecord : 1
             * passWord : d41d8cd98f00b204e9800998ecf8427e
             * registerip :
             * status : 1
             * transferall :
             * transferbusy :
             * transfernoanswer :
             * transferoffline :
             * userName :
             */

            private String deptName;
            private String displayName;
            private String employeeName;
            private String exten;
            private String extenPassword;
            private String mobile;
            private String needRecord;
            private String passWord;
            private String registerip;
            private String status;
            private String transferall;
            private String transferbusy;
            private String transfernoanswer;
            private String transferoffline;
            private String userName;

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }

            public String getEmployeeName() {
                return employeeName;
            }

            public void setEmployeeName(String employeeName) {
                this.employeeName = employeeName;
            }

            public String getExten() {
                return exten;
            }

            public void setExten(String exten) {
                this.exten = exten;
            }

            public String getExtenPassword() {
                return extenPassword;
            }

            public void setExtenPassword(String extenPassword) {
                this.extenPassword = extenPassword;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getNeedRecord() {
                return needRecord;
            }

            public void setNeedRecord(String needRecord) {
                this.needRecord = needRecord;
            }

            public String getPassWord() {
                return passWord;
            }

            public void setPassWord(String passWord) {
                this.passWord = passWord;
            }

            public String getRegisterip() {
                return registerip;
            }

            public void setRegisterip(String registerip) {
                this.registerip = registerip;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTransferall() {
                return transferall;
            }

            public void setTransferall(String transferall) {
                this.transferall = transferall;
            }

            public String getTransferbusy() {
                return transferbusy;
            }

            public void setTransferbusy(String transferbusy) {
                this.transferbusy = transferbusy;
            }

            public String getTransfernoanswer() {
                return transfernoanswer;
            }

            public void setTransfernoanswer(String transfernoanswer) {
                this.transfernoanswer = transfernoanswer;
            }

            public String getTransferoffline() {
                return transferoffline;
            }

            public void setTransferoffline(String transferoffline) {
                this.transferoffline = transferoffline;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
