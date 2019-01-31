/**
 * 
 */
package com.xzc.web.shiro;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xzc.common.bean.Permission;
import com.xzc.common.bean.Relationship;
import com.xzc.common.bean.Role;
import com.xzc.manager.service.PermissionService;
import com.xzc.manager.service.RoleService;

/**
 * @author xiezengcheng
 * @version 2019年1月31日 上午10:32:29
 */
public class DynamicPermission {

	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	
	public Map<String,String> dynamicSetPermission(Map<String,String> filterMap){
		
		//获取所有的角色
		List<Role> roles = roleService.queryAll();
		List<Permission> permissions = permissionService.queryAll();
		List<Relationship> relationships = roleService.getAllRelationship();
		for (Role role : roles) {
			for (Relationship relationship : relationships) {
				if(role.getId()==relationship.getRoleid()) {
					for (Permission permission : permissions) {
						if(permission.getId()==relationship.getPermissionid()) {
							String roleName = filterMap.get(permission.getUrl());
							if(roleName!=null) {						
								roleName += ","+role.getName();
							}else {
								roleName = "roles["+role.getName();
							}
							filterMap.put(permission.getUrl(),roleName);
						}
					}
				}
			}
		}
		for (Permission permission : permissions) {		
				String roleName = filterMap.get(permission.getUrl());
				if(roleName!=null) {
					roleName += "]"; 
					filterMap.put(permission.getUrl(),roleName);
				}
		}
		return filterMap;
	}
	
}
