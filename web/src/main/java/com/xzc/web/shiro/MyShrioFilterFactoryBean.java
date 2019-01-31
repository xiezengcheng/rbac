/**
 * 
 */
package com.xzc.web.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xzc.common.bean.Permission;
import com.xzc.common.bean.Relationship;
import com.xzc.common.bean.Role;
import com.xzc.manager.service.PermissionService;
import com.xzc.manager.service.RoleService;
import com.xzc.manager.service.serviceImpl.RoleServiceImpl;

/**
 * 重写ShiroFilterFactoryBean中的setFilterChainDefinitionMap方法
 * 实现动态加载权限
 * @author xiezengcheng
 * @version 2019年1月30日 下午8:08:43
 */
public class MyShrioFilterFactoryBean extends ShiroFilterFactoryBean{

	
	@Override
	public void setFilterChainDefinitionMap(Map<String, String> filterMap) {	
		super.setFilterChainDefinitionMap(filterMap);
	}
	
}
