package com.handinglian.extension.mapper;

import com.handinglian.extension.entity.CallRecord;
import java.util.Collection;import java.util.Date;import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CallRecordMapper {
    int deleteByPrimaryKey(Integer callRecordId);

    int insert(CallRecord record);

    int insertOrUpdate(CallRecord record);

    int insertOrUpdateSelective(CallRecord record);

    int insertSelective(CallRecord record);

    CallRecord selectByPrimaryKey(Integer callRecordId);

    int updateByPrimaryKeySelective(CallRecord record);

    int updateByPrimaryKey(CallRecord record);

    int updateBatch(List<CallRecord> list);

    int batchInsert(@Param("list") List<CallRecord> list);

    List<CallRecord> inquireCallRecordPageList(@Param("minStartTime") Date minStartTime, @Param("maxStartTime") Date maxStartTime, @Param("callerNo") String callerNo, @Param("calledNo") String calledNo, @Param("recordStatus") Integer recordStatus, @Param("phoneStatus") Integer phoneStatus, @Param("hasRecord") Integer hasRecord);

    List<String> findFileUuidByReqidIn(@Param("reqidCollection") Collection<String> reqidCollection);

    List<String> findReqidByStartTimeBetween(@Param("minStartTime") Date minStartTime, @Param("maxStartTime") Date maxStartTime);
}