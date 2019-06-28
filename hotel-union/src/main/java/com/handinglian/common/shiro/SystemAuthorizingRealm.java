package com.handinglian.common.shiro;


import com.handinglian.system.entity.UserInfo;
import com.handinglian.system.service.UserInfoService;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Fly
 * @Description
 * @Date Created in 9:20 2017/8/31
 * @Modified by
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 认证回调函数, 登录时调用 设置了authc才会进入
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 校验用户名密码
        UserInfo user = userInfoService.loadUserInfoByJobNum(token.getUsername());
        if (user != null) {
            //如果身份认证验证成功，返回一个AuthenticationInfo实现；
            SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo(user.getJobNum(), user.getPassword(), getName());
            ai.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt())); //盐是用户名+随机数
            return ai;
        }else {
            return null;
        }
    }

    /**
     * 获取权限授权信息，如果缓存中存在，则直接从缓存中获取，否则就重新获取， 登录成功后调用
     */
    @Override
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            return null;
        }
        //实际项目中这里可以设置缓存，从缓存中读取
        return doGetAuthorizationInfo(principals);
    }
    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Principal principal = (Principal) getAvailablePrincipal(principals);

        UserInfo user = userInfoService.loadUserInfoByJobNum(principal.getUserName());
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<com.handinglian.system.entity.Permission> list = userInfoService.inquirePermissionList(user.getJobNum());
            for (com.handinglian.system.entity.Permission permission : list){
                if (StringUtils.isNotBlank(permission.getPermission())){
                    // 添加基于Permission的权限信息
                    for (String permi : StringUtils.split(permission.getPermission(),",")){
                        info.addStringPermission(permi);
                    }
                }
            }
            // 添加用户权限
            info.addStringPermission("user");
            return info;
        } else {
            return null;
        }
    }

    @Override
    protected void checkPermission(Permission permission, AuthorizationInfo info) {
        authorizationValidate(permission);
        super.checkPermission(permission, info);
    }

    @Override
    protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
        if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
                authorizationValidate(permission);
            }
        }
        return super.isPermitted(permissions, info);
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, Permission permission) {
        authorizationValidate(permission);
        return super.isPermitted(principals, permission);
    }

    @Override
    protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
        if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
                authorizationValidate(permission);
            }
        }
        return super.isPermittedAll(permissions, info);
    }

    /**
     * 授权验证方法
     * @param permission
     */
    private void authorizationValidate(Permission permission){
        // 模块授权预留接口
    }

    /**
     * 授权用户信息
     */
    public static class Principal implements Serializable {

        private static final long serialVersionUID = 1L;

        private Integer id;
        private String userName;

        public Principal(UserInfo user) {
            this.id = user.getUserId();
            this.userName = user.getJobNum();
        }

        public Integer getId() {
            return id;
        }

        public String getUserName() {
            return userName;
        }

        @Override
        public String toString() {
            return "Principal{" +
                    "id=" + id +
                    '}';
        }
    }
}
