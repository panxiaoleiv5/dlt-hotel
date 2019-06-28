package com.handinglian.kongke.dto;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: pxl
 * @create: 2019/6/5 12:01
 */
@Data
public class KkIntelligentNodeJsonDto {

    private List<ZigbeeDevicesBean> zigbeeDevices;

    @Data
    public static class ZigbeeDevicesBean {
        /**
         * mac : 00:12:4B:00:0E:FD:12:5C
         * gwMac : 0E:FD:12:5C: 00:12:4B
         * onlineState : ONLINE
         * lastJoinTime : 2018-05-22 15:44:39
         * version : 2.1.0
         * productId : 3
         * channelDevices : [{"id":2131,"type":"KONKE_ZIGBEE_LIGHT","channel":1,"status":{"on":true}}]
         */

        private String mac;
        private String gwMac;
        private String onlineState;
        private String lastJoinTime;
        private String version;
        private int productId;
        private List<ChannelDevicesBean> channelDevices;

        @Data
        public static class ChannelDevicesBean {
            /**
             * id : 2131
             * type : KONKE_ZIGBEE_LIGHT
             * channel : 1
             * status : {"on":true}
             */

            private int id;
            private String type;
            private int channel;
            private StatusBean status;

            @Data
            public static class StatusBean {
                /**
                 * on : true
                 */

                private boolean on;
            }
        }
    }
}
