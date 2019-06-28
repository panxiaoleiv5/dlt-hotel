package com.handinglian.system.controller;

import com.apidoc.annotation.Api;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.factory.ResultDataFactory;
import com.handinglian.system.entity.Department;
import com.handinglian.system.service.DepartmentService;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Api("部门")
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 创建部门
     */
    @PostMapping("/createDepartment")
    public ResultData createDepartment(String departmentName){
        Department department = departmentService.loadInvalidDepartment(departmentName);
        if (department != null){
            return ResultDataFactory.generateExistInDeleteResultData();
        } else {
            int amount = departmentService.createDepartment(departmentName);
            return ResultDataFactory.generateResultData(amount);
        }
    }

    /**
     * 从删除列表中恢复部门
     */
    @PutMapping(value = "/recoverDepartment")
    public ResultData recoverDepartment(String departmentName) {
        int amount = departmentService.recoverDepartment(departmentName);
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 删除部门
     */
    @DeleteMapping(value = "/deleteDepartment")
    public ResultData deleteDepartment(Integer departmentId) throws IOException, DocumentException {
        int amount = departmentService.deleteDepartment(departmentId);
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 更新部门
     */
    @PutMapping(value = "/updateDepartment")
    public ResultData updateDepartment(Integer departmentId, String departmentName) {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setDepartmentName(departmentName);
        department.setUpdateTime(new Date());

        int amount = departmentService.updateDepartment(department);
        return ResultDataFactory.generateResultData(amount);
    }


    /**
     * 查询部门
     */
    @GetMapping(value = "/loadDepartment")
    public ResultData<Department> loadDepartment(Integer departmentId) {
        Department department = departmentService.loadDepartment(departmentId);
        return ResultDataFactory.generateSuccessResultData(department);
    }

    /**
     * 获取部门列表
     */
    @GetMapping("/inquireDepartmentList")
    public ResultData<Department> inquireDepartmentPageList() {
        List<Department> departmentList = departmentService.inquireDepartmentList();
        return ResultDataFactory.generateSuccessResultData(departmentList);
    }

}
