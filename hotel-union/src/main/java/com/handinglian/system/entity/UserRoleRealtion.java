package com.handinglian.system.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class UserRoleRealtion implements Serializable {
    private Integer userId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
}