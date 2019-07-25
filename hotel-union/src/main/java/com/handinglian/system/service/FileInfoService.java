package com.handinglian.system.service;

import com.handinglian.system.entity.FileInfo;

import java.util.List;

public interface FileInfoService {
    List<FileInfo> inquireFileInfoList(List<String> fileUuids);
}
