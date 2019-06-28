package com.apidoc.dao;

import com.apidoc.entity.ApidocInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 文件基本信息 Mapper 接口
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-09-11
 */
public interface ApidocInfoDao extends BaseMapper<ApidocInfo> {

    @Select("select * from `apidoc_info` where `packageName`=#{packageName}")
    ApidocInfo selectByPackageName(@Param("packageName") String packageName);

    @Select("${sql}")
    boolean exeSql(@Param("sql") String sql);
}
