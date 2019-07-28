package com.handinglian.system.mapper;
import java.util.Collection;

import com.handinglian.system.entity.FileInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileInfoMapper {
    int deleteByPrimaryKey(String fileUuid);

    int insert(FileInfo record);

    int insertOrUpdate(FileInfo record);

    int insertOrUpdateSelective(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(String fileUuid);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    int updateBatch(List<FileInfo> list);

    int batchInsert(@Param("list") List<FileInfo> list);

    List<FileInfo> findByFileUuidIn(@Param("fileUuidCollection")Collection<String> fileUuidCollection);
}