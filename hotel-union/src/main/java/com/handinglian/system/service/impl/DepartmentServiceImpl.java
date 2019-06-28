package com.handinglian.system.service.impl;

import com.handinglian.common.enums.ValidEnum;
import com.handinglian.system.entity.Department;
import com.handinglian.system.mapper.DepartmentMapper;
import com.handinglian.system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    @Transactional
    public int createDepartment(String departmentName) {
        Date now = new Date();
        Department department = new Department();
        department.setDepartmentName(departmentName);
        department.setCreateTime(now);
        department.setUpdateTime(now);
        return departmentMapper.insertSelective(department);
    }

    @Override
    public Department loadInvalidDepartment(String departmentName) {
        return departmentMapper.findInvalidOneByDepartmentName(departmentName);
    }

    @Override
    @Transactional
    public int recoverDepartment(String departmentName) {
        Department department = departmentMapper.findInvalidOneByDepartmentName(departmentName);
        department.setValid(ValidEnum.VALID.getKey());

        return updateDepartment(department);
    }

    @Override
    @Transactional
    public int updateDepartment(Department department) {
        return departmentMapper.updateByPrimaryKeySelective(department);
    }

    @Override
    @Transactional
    public int deleteDepartment(Integer departmentId) {
        Department department = departmentMapper.selectByPrimaryKey(departmentId);
        department.setValid(ValidEnum.INVALID.getKey());
        return departmentMapper.updateByPrimaryKeySelective(department);
    }

    @Override
    public Department loadDepartment(Integer departmentId) {
        return departmentMapper.selectByPrimaryKey(departmentId);
    }

    @Override
    public List<Department> inquireDepartmentList() {
        return departmentMapper.find();
    }
}
