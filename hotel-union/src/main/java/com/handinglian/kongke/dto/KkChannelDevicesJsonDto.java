package com.handinglian.kongke.dto;

import lombok.Data;

/**
 * @description:
 * @author: pxl
 * @create: 2019/6/5 17:14
 */
@Data
public class KkChannelDevicesJsonDto {
    private int id;
    private String type;
    private int channel;
    private StatusBean status;
    private boolean onlineState;

    @Data
    public static class StatusBean {
        /**
         * on : true
         */

        private boolean on;
    }
}
