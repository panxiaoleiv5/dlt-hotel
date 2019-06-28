package com.handinglian.contentunion.mapper;

import com.handinglian.contentunion.entity.Gateway;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GatewayMapper {
    int deleteByPrimaryKey(Integer gatewayId);

    int insert(Gateway record);

    int insertOrUpdate(Gateway record);

    int insertOrUpdateSelective(Gateway record);

    int insertSelective(Gateway record);

    Gateway selectByPrimaryKey(Integer gatewayId);

    int updateByPrimaryKeySelective(Gateway record);

    int updateByPrimaryKey(Gateway record);

    int updateBatch(List<Gateway> list);

    int batchInsert(@Param("list") List<Gateway> list);

    Gateway findOneByCentralHostId(@Param("centralHostId") Integer centralHostId);
}