package com.handinglian.kongke.dto;

import lombok.Data;

/**
 * @description:
 * @author: pxl
 * @create: 2019/6/4 16:02
 */
@Data
public class KkIntelligentDeviceJsonDto {


    /**
     * id : 3
     * type : KONKE_ZIGBEE_LIGHT
     * status : {"onlineState":"ONLINE","on":true}
     * extraInfo : {"mac":"00:12:4B:00:18:17:14:27","channel":1,"gwMac":"00:12:4B:00:19:A0:03:73"}
     * lastJoinTime : 2019-06-05 11:45:15.0
     */

    private int id;
    private String type;
    private ExtraInfoBean extraInfo;
    private String lastJoinTime;

    @Data
    public static class ExtraInfoBean {
        /**
         * mac : 00:12:4B:00:18:17:14:27
         * channel : 1
         * gwMac : 00:12:4B:00:19:A0:03:73
         */

        private String mac;
        private int channel;
        private String gwMac;
    }
}
