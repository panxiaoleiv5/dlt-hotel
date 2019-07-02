package com.handinglian.system.dto;

import lombok.Data;

@Data
public class LoginDto {
    /**
     * 工号
     */
    private String username;

    /**
     * 密码（需加盐加密操作）
     */
    private String password;
}
