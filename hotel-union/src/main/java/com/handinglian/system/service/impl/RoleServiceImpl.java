package com.handinglian.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.handinglian.common.enums.ValidEnum;
import com.handinglian.common.utils.GeneralUtil;
import com.handinglian.common.utils.StringUtil;
import com.handinglian.system.dto.MenuPermissionDto;
import com.handinglian.system.entity.Menu;
import com.handinglian.system.entity.Role;
import com.handinglian.system.entity.RolePermissionRelation;
import com.handinglian.system.mapper.RoleMapper;
import com.handinglian.system.service.MenuService;
import com.handinglian.system.service.PermissionService;
import com.handinglian.system.service.RolePermissionService;
import com.handinglian.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("/roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    @Transactional
    public int createRole(String roleName) {
        Date now = new Date();
        Role role = new Role();
        role.setRoleName(roleName);
        role.setCreateTime(now);
        role.setUpdateTime(now);

        return roleMapper.insertSelective(role);
    }

    @Override
    public Role loadInvalidRole(String roleName) {
        return roleMapper.findInvalidOneByRoleName(roleName);
    }

    @Override
    @Transactional
    public int recoverRole(String roleName) {
        Role role = roleMapper.findInvalidOneByRoleName(roleName);
        role.setValid(ValidEnum.VALID.getKey());

        return updateRole(role);
    }

    @Override
    @Transactional
    public int updateRole(Integer roleId, String roleName) {
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        role.setUpdateTime(new Date());

        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    @Transactional
    public int updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    @Transactional
    public int deleteRole(Integer roleId) {
        Role role = roleMapper.selectByPrimaryKey(roleId);
        role.setValid(ValidEnum.INVALID.getKey());
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Role loadRole(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public PageInfo<Role> inquireRolePageList(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 1 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;

        PageHelper.startPage(pageIndex, pageSize);
        return new PageInfo<>(roleMapper.find());
    }

    @Override
    public Role loadRoleByRoleName(String roleName) {
        return roleMapper.findOneByRoleName(roleName);
    }

    @Override
    public List<MenuPermissionDto> inquireMenuAndPermissionList() {
        List<MenuPermissionDto> menuOneLevel = menuService.inquireLevelMenuList(1);
        List<MenuPermissionDto> menuTwoLevel= menuService.inquireLevelMenuList(2);

        //添加二级目录
        for (MenuPermissionDto menuPermissionDto : menuOneLevel){
            for (MenuPermissionDto menuPermissionSubDto : menuTwoLevel){
                menuPermissionDto.addDepartment(menuPermissionSubDto);
            }
        }
        return menuOneLevel;
    }

    @Override
    @Transactional
    public void addOrDeletePermission(Integer roleId, String permissionIds) {
        List<Integer> paramPermissionId = StringUtil.stringToIntegerList(permissionIds);
        List<Integer> dbPermissionId = rolePermissionService.inquirePermissionIdList(roleId);

        List<Integer> addList = GeneralUtil.getAddList(paramPermissionId, dbPermissionId);
        List<Integer> deleteList = GeneralUtil.getMissingList(paramPermissionId, dbPermissionId);

        for (Integer addId : addList){
            RolePermissionRelation rolePermissionRelation = new RolePermissionRelation();
            rolePermissionRelation.setRoleId(roleId);
            rolePermissionRelation.setPermissionId(addId);
            rolePermissionService.createRolePermissionRelation(rolePermissionRelation);
        }

        for (Integer deleteId : deleteList){
            rolePermissionService.deleteRolePermission(roleId, deleteId);
        }
    }

}
