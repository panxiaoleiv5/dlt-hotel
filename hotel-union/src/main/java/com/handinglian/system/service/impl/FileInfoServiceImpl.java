package com.handinglian.system.service.impl;

import com.handinglian.system.entity.FileInfo;
import com.handinglian.system.mapper.FileInfoMapper;
import com.handinglian.system.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fileInfoService")
public class FileInfoServiceImpl implements FileInfoService {
    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public List<FileInfo> inquireFileInfoList(List<String> fileUuids) {
        return fileInfoMapper.findByFileUuidIn(fileUuids);
    }
}
