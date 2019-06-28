package com.handinglian.contentunion.mapper;

import com.handinglian.contentunion.entity.IntelligentSubDevice;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

@Mapper
public interface IntelligentSubDeviceMapper {
    int deleteByPrimaryKey(Integer intelligentSubDeviceId);

    int insert(IntelligentSubDevice record);

    int insertSelective(IntelligentSubDevice record);

    IntelligentSubDevice selectByPrimaryKey(Integer intelligentSubDeviceId);

    int updateByPrimaryKeySelective(IntelligentSubDevice record);

    int updateByPrimaryKey(IntelligentSubDevice record);

    List<IntelligentSubDevice> findByIntelligentDeviceId(@Param("intelligentDeviceId") Integer intelligentDeviceId);

    int deleteByIntelligentDeviceId(@Param("intelligentDeviceId") Integer intelligentDeviceId);

    List<String> inquireSubDeviceNameGroupBySubDeviceName();
}