package com.handinglian.contentunion.mapper;

import com.handinglian.contentunion.dto.IntelligentDeviceReturnDto;import com.handinglian.contentunion.entity.IntelligentDevice;
import java.util.List;
import com.handinglian.contentunion.entity.ProductList;import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IntelligentDeviceMapper {
    int deleteByPrimaryKey(Integer intelligentDeviceId);

    int insert(IntelligentDevice record);

    int insertOrUpdate(IntelligentDevice record);

    int insertOrUpdateSelective(IntelligentDevice record);

    int insertSelective(IntelligentDevice record);

    IntelligentDevice selectByPrimaryKey(Integer intelligentDeviceId);

    int updateByPrimaryKeySelective(IntelligentDevice record);

    int updateByPrimaryKey(IntelligentDevice record);

    int updateBatch(List<IntelligentDevice> list);

    int batchInsert(@Param("list") List<IntelligentDevice> list);

    int insertSelectiveWithPrimaryKey(IntelligentDevice record);

    IntelligentDevice findInvalidOneByMacIp(@Param("macIp") String macIp);

    List<IntelligentDevice> find();

    List<IntelligentDeviceReturnDto> inquireIntelligentExtensionPageList();

    int updateGwMacAndKkTypeByMacIp(@Param("updatedGwMac") String updatedGwMac, @Param("updatedKkType") String updatedKkType, @Param("macIp") String macIp);

    List<IntelligentDevice> findByCentralHostId(@Param("centralHostId") Integer centralHostId);

    List<ProductList> inquireProductList();
}