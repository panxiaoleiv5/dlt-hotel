package com.handinglian.kongke.dto;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: pxl
 * @create: 2019/6/3 11:29
 */
@Data
public class KkHostBaseInfoJsonDto {

    /**
     * localIp : 192.168.0.123
     * curVersion : 2.25.3
     * downloadVersion : 2.26.1
     * gwList : [{"gwId":2,"gwName":"poe","gwMac":"1B:2C:34:E0:D9:3F","gwIp":"1B2","gwOnline":true,"gwType":1,"gwCurVersion":"2.5.3","gwDownloadVersion":"2.6.1"}]
     */

    private String localIp;
    private String curVersion;
    private String downloadVersion;
    private List<GateWayBean> gwList;


    @Data
    public static class GateWayBean {
        /**
         * gwId : 2
         * gwName : poe
         * gwMac : 1B:2C:34:E0:D9:3F
         * gwIp : 1B2
         * gwOnline : true
         * gwType : 1
         * gwCurVersion : 2.5.3
         * gwDownloadVersion : 2.6.1
         */

        private int gwId;
        private String gwName;
        private String gwMac;
        private String gwIp;
        private boolean gwOnline;
        private int gwType;
        private String gwCurVersion;
        private String gwDownloadVersion;
    }
}
