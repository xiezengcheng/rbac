/**
 * 
 */
package com.xzc.web.shiro;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzc.common.bean.Permission;
import com.xzc.manager.service.PermissionService;

/**
 * @author xiezengcheng
 * @version 2019年1月31日 上午11:11:30
 */


public class MyUserFilter extends org.apache.shiro.web.filter.authc.UserFilter  {
	@Autowired
	MyShrioFilterFactoryBean myShiroFilter;
	/* (non-Javadoc)
	 * @see org.apache.shiro.web.filter.authc.UserFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rese = (HttpServletResponse)response;
		String uri = req.getServletPath();
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            if(subject.isAuthenticated()&&subject.isPermitted(uri)) {         	
            	return true;
            }
            if(subject.isAuthenticated()) {
            	Map<String,String> map = myShiroFilter.getFilterChainDefinitionMap();
            	System.out.println(map.get("/**"));
            	return true;
//            	map.containsKey(key)
//            	subject.getPrincipals()
            }else {
            	return false;
            }
        }
	}
	
}
