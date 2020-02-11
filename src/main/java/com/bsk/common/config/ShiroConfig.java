package com.bsk.common.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

//表示这是一个配置类，交给spring来管理
@Configuration
public class ShiroConfig  {

    @Bean
    public SecurityManager securitManager(Realm realm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory (@Autowired SecurityManager securityManager) {
        ShiroFilterFactoryBean sfBean= new ShiroFilterFactoryBean();
        sfBean.setSecurityManager(securityManager);
        //假如没有认证请求先访问此认证的url
        sfBean.setLoginUrl("login");
        //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
        LinkedHashMap<String,String> map= new LinkedHashMap<>();
        //静态资源允许匿名访问:"anon"
        map.put("/**","anon");
        //除了匿名访问的资源,其它都要认证("authc")后访问
        //map.put("/**","authc");
        sfBean.setFilterChainDefinitionMap(map);
        return sfBean;
    }



}
