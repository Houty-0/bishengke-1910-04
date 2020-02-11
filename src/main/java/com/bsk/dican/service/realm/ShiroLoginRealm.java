package com.bsk.dican.service.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.bsk.dican.dao.BskUserDao;
import com.bsk.dican.entity.BskUser;

@Service
public class ShiroLoginRealm extends AuthorizingRealm {

	@Autowired
	private BskUserDao bskUserDao;
	// 权限认证不需要

	/**
	 * 凭证匹配器
	 * 
	 * @param credentialsMatcher
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		// 加密算法
		matcher.setHashAlgorithmName("MD5");
		// 加密次数
		matcher.setHashIterations(1);
		super.setCredentialsMatcher(matcher);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	// 重写登录的认证的方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;

		String phone = userToken.getUsername();
		BskUser user = bskUserDao.findObjectByPhone(phone);
		System.err.println("Shirophone" + phone);
		System.err.println("ShiroUser" + user);
		// 1.校验结果
		if (user == null) {
			throw new UnknownAccountException();
		}

		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,
				user.getPassword(), credentialsSalt, getName());
		return info;
	}
	
//	@Bean
//	public SecurityManager securityManager(Realm realm, CacheManager cacheManager, 
//			RememberMeManager rememberMeManager, SessionManager sessionManager) {
//		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
//		sManager.setRealm(realm);
////		sManager.setCacheManager(cacheManager);
////		sManager.setRememberMeManager(rememberMeManager);
//		sManager.setSessionManager(sessionManager);
//		return sManager;
//	}
//
//	// shiro授权配置
//	@Bean
//	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//
//		advisor.setSecurityManager(securityManager);
//		return advisor;
//	}
}
