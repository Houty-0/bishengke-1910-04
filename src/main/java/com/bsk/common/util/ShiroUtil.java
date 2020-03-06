package com.bsk.common.util;

import org.apache.shiro.SecurityUtils;

import com.bsk.dican.entity.BskUser;

public class ShiroUtil {

	
    /**
     * 获取登录用户的Id
     * @return
     */
    public static Integer getUserId() {
        return getLoginUser().getId();
    }

    /**
     * 获取登录用户的用户名
     * @return
     */
//    public static String getUsername() {
//        return getLoginUser().getUsername();
//    }

    /**
     * 获取登录用户对象
     * @return
     */
    public static BskUser getLoginUser() {

        return (BskUser) SecurityUtils.getSubject().getPrincipal();
    }
}
