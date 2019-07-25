package com.handinglian.extension.service.impl;

import com.handinglian.extension.entity.AreaCode;
import com.handinglian.extension.mapper.AreaCodeMapper;
import com.handinglian.extension.service.AreaCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("areaCodeService")
public class AreaCodeServiceImpl implements AreaCodeService {
    @Autowired
    private AreaCodeMapper areaCodeMapper;

    @Override
    public AreaCode loadAreaCode(Integer areaCodeId) {
        return areaCodeMapper.selectByPrimaryKey(areaCodeId);
    }

    @Override
    public List<String> inquireAreaCodeList() {
        return areaCodeMapper.findAreaCode();
    }
}
