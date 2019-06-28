package com.handinglian.system.mapper;

import com.handinglian.system.entity.Department;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer departmentId);

    int insert(Department record);

    int insertOrUpdate(Department record);

    int insertOrUpdateSelective(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer departmentId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    int updateBatch(List<Department> list);

    int batchInsert(@Param("list") List<Department> list);

    Department findInvalidOneByDepartmentName(@Param("departmentName")String departmentName);

    Department findOneByDepartmentName(@Param("departmentName")String departmentName);

    List<Department> find();
}