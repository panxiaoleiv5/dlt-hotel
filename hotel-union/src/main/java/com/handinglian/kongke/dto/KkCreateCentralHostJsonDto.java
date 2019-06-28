package com.handinglian.kongke.dto;

public class KkCreateCentralHostJsonDto {

    /**
     * id : 139a1a4a-d3f1-4988-aad0-09b3228d7e93
     * productId : 7
     * deviceId : CCU_03483
     * online : false
     */

    private String id;
    private String productId;
    private String deviceId;
    private boolean online;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
