package com.handinglian.system.controller;

import com.apidoc.annotation.Api;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.dto.ResultData;
import com.handinglian.common.factory.ResultDataFactory;
import com.handinglian.system.dto.MenuPermissionDto;
import com.handinglian.system.entity.Role;
import com.handinglian.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api("角色")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 创建角色
     */
    @PostMapping("/createRole")
    public ResultData createRole(String roleName){
        Role role = roleService.loadInvalidRole(roleName);
        if (role != null){
            return ResultDataFactory.generateExistInDeleteResultData();
        } else {
            int amount = roleService.createRole(roleName);
            return ResultDataFactory.generateResultData(amount);
        }
    }

    /**
     * 从删除列表中恢复角色
     */
    @PutMapping(value = "/recoverRole")
    public ResultData recoverRole(String roleName) {
        int amount = roleService.recoverRole(roleName);
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 删除角色
     */
    @DeleteMapping(value = "/deleteRole")
    public ResultData deleteRole(Integer roleId) {
        int amount = roleService.deleteRole(roleId);
        return ResultDataFactory.generateResultData(amount);
    }

    /**
     * 更新角色
     */
    @PutMapping(value = "/updateRole")
    public ResultData updateRole(Integer roleId, String roleName) {
        int amount = roleService.updateRole(roleId, roleName);
        return ResultDataFactory.generateResultData(amount);
    }


    /**
     * 查询角色
     */
    @GetMapping(value = "/loadRole")
    public ResultData<Role> loadRole(Integer roleId) {
        Role role = roleService.loadRole(roleId);
        return ResultDataFactory.generateSuccessResultData(role);
    }

    /**
     * 获取角色列表
     */
    @GetMapping("/inquireRolePageList")
    public ResultData<PageInfo<Role>> inquireRolePageList(Integer pageIndex, Integer pageSize) {
        PageInfo<Role> rolePageInfo = roleService.inquireRolePageList(pageIndex, pageSize);
        return ResultDataFactory.generateSuccessResultData(rolePageInfo);
    }

    /**
     * 获取权限菜单列表
     */
    @GetMapping("/inquireMenuAndPermissionList")
    public ResultData<MenuPermissionDto> inquireMenuAndPermissionList() {
        List<MenuPermissionDto> menuPermissionDtos = roleService.inquireMenuAndPermissionList();
        return ResultDataFactory.generateSuccessResultData(menuPermissionDtos);
    }

    /**
     * 增加或删除权限
     */
    @PostMapping("/addOrDeletePermission")
    public ResultData addOrDeletePermission(Integer roleId, String permissionIds) {
        roleService.addOrDeletePermission(roleId, permissionIds);
        return ResultDataFactory.generateSuccessResultData(null);
    }

}
