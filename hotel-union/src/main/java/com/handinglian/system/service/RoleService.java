package com.handinglian.system.service;

import com.github.pagehelper.PageInfo;
import com.handinglian.system.dto.MenuPermissionDto;
import com.handinglian.system.entity.Role;

import java.util.List;

public interface RoleService {
    int createRole(String roleName);

    /**
     * 查询被删除的角色
     * @author pxl
     * @param roleName
     * @return com.handinglian.contentunion.entity.CentralHost
     * @date 2019/6/3 10:49
     */
    Role loadInvalidRole(String roleName);

    /**
     * 恢复删除的角色
     * @author pxl
     * @param roleName
     * @return int
     * @date 2019/6/3 17:56
     */
    int recoverRole(String roleName);

    int updateRole(Integer roleId, String roleName);

    int updateRole(Role role);

    int deleteRole(Integer roleId);

    Role loadRole(Integer roleId);

    /**
     * 分页查询角色列表
     * @author pxl
     * @param pageIndex
     * @param pageSize
     * @return com.github.pagehelper.PageInfo<com.handinglian.contentunion.entity.CentralHost>
     * @date 2019/6/3 10:48
     */
    PageInfo<Role> inquireRolePageList(Integer pageIndex, Integer pageSize);

    Role loadRoleByRoleName(String roleName);

    List<MenuPermissionDto> inquireMenuAndPermissionList();

    void addOrDeletePermission(Integer roleId, String permissionIds);
}
