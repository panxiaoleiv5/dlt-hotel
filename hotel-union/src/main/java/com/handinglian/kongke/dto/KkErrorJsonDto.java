package com.handinglian.kongke.dto;

import lombok.Data;

@Data
public class KkErrorJsonDto {

    /**
     * error_code : -10010
     * error_msg : Res not exist
     * data : 139a1a4a-d3f1-4988-aad0-09b3228d7e93 not bind to any user
     */

    private int error_code;
    private String error_msg;
    private String data;
}

