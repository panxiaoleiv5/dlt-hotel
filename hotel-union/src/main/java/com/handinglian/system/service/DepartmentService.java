package com.handinglian.system.service;

import com.handinglian.system.entity.Department;

import java.util.List;

public interface DepartmentService {
    int createDepartment(String departmentName);

    /**
     * 查询被删除的部门
     * @author pxl
     * @param departmentName
     * @return com.handinglian.contentunion.entity.CentralHost
     * @date 2019/6/3 10:49
     */
    Department loadInvalidDepartment(String departmentName);

    /**
     * 恢复删除的部门
     * @author pxl
     * @param departmentName
     * @return int
     * @date 2019/6/3 17:56
     */
    int recoverDepartment(String departmentName);

    int updateDepartment(Department department);

    int deleteDepartment(Integer departmentId);

    Department loadDepartment(Integer departmentId);

    List<Department> inquireDepartmentList();
}
