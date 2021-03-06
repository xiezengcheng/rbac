/**
 * 
 */
package com.xzc.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xzc.common.bean.Permission;
import com.xzc.manager.service.PermissionService;

/**
 * @author xiezengcheng
 * @version 2019年1月14日 下午12:27:25
 */
public class PermissionInterceptor implements HandlerInterceptor {

	@Resource
	private PermissionService permissionService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//获取用户的请求地址
		String uri = request.getRequestURI();
		
		
		
		//判断当前的路径是否需要进行权限拦截
		
		
		
		String path = request.getSession().getServletContext().getContextPath();
		//查询所欲需要验证的路径集合
		List<Permission> permissions = permissionService.queryAll();
		Set<String> uriSet = new HashSet<String>();	
		for (Permission permission : permissions) {
			if(permission.getUrl() != null && !permission.getUrl().equals("")) {
				uriSet.add(path+permission.getUrl());
			}
		}
		
		if(uriSet.contains(uri)) {
			//权限验证
			//判断当前的用户是否拥有对应的权限
			Set<String> authUriSet = (Set<String>) request.getSession().getAttribute("authUriSet");
			if(authUriSet.contains(uri)) {
				return true;
			}else {
				response.sendRedirect(path+"/error");
				return false;
			}
			
		}else {
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
}
