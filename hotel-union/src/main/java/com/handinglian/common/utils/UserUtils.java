package com.handinglian.common.utils;

import com.handinglian.common.shiro.SystemAuthorizingRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

/**
 * @author Fly
 * @Description
 * @Date Created in 8:36 2017/12/21
 * @Modified by
 */
public class UserUtils {

    /**
     * 获取当前登录者对象
     */
    public static String getPrincipal() {
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        return principal == null?null:principal;
    }

}
