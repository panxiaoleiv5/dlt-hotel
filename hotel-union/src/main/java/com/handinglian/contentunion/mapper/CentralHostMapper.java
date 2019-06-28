package com.handinglian.contentunion.mapper;

import com.handinglian.contentunion.entity.CentralHost;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

@Mapper
public interface CentralHostMapper {
    int deleteByPrimaryKey(Integer centralHostId);

    int insert(CentralHost record);

    int insertSelective(CentralHost record);

    CentralHost selectByPrimaryKey(Integer centralHostId);

    int updateByPrimaryKeySelective(CentralHost record);

    int updateByPrimaryKey(CentralHost record);

    CentralHost findInvalidOneByMacIp(@Param("macIp") String macIp);

    List<CentralHost> find();

    CentralHost findOneByMacIp(@Param("macIp")String macIp);


}