package com.handinglian.extension.service;

import com.handinglian.extension.entity.AreaCode;

import java.util.List;

public interface AreaCodeService {
    AreaCode loadAreaCode(Integer areaCodeId);

    List<String> inquireAreaCodeList();
}
