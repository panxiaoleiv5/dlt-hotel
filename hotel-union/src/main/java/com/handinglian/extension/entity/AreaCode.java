package com.handinglian.extension.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class AreaCode implements Serializable {
    private Integer areaCodeId;

    private String cityName;

    private String areaCode;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}