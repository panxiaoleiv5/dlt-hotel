package com.handinglian.yunyi.dto;

import lombok.Data;

import java.util.List;

@Data
public class YyGetExtensionJsonDto {

    /**
     * message : [{"deptName":"A组","displayName":"801","employeeName":"801","exten":"801","extenPassword":"admin246","mobile":"","needRecord":"1","passWord":"d41d8cd98f00b204e9800998ecf8427e","registerip":"","status":"1","transferall":"","transferbusy":"","transfernoanswer":"","transferoffline":"","userName":""}]
     * statuscode : 000000
     */

    private String statuscode;
    private List<MessageBean> message;

    @Data
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
    }
}
