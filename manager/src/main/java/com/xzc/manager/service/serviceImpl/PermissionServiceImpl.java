package com.xzc.manager.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzc.common.bean.Permission;
import com.xzc.common.bean.User;
import com.xzc.manager.dao.PermissionDAO;
import com.xzc.manager.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionDAO permissionDAO;

	public Permission queryRootPermission() {
		return permissionDAO.queryRoot();
	}

	
	public List<Permission> queryChildPermissions(Integer pid) {
		return permissionDAO.queryChildren(pid);
	}


	public List<Permission> queryAll() {
		return permissionDAO.queryAll();
	}


	public void insertPermission(Permission permission) {
		permissionDAO.insert(permission);
	}


	public Permission queryById(Integer id) {
		// TODO Auto-generated method stub
		return permissionDAO.queryById(id);
	}


	public void updatePermission(Permission permission) {
		// TODO Auto-generated method stub
		permissionDAO.update(permission);
	}


	public void deletePermission(Integer id) {
		permissionDAO.delete(id);
	}


	public List<Integer> queryPermissionidsByRoleid(Integer roleid) {
		// TODO Auto-generated method stub
		return permissionDAO.queryPermissionsidByRoleid(roleid);
	}


	public List<Permission> queryPermissionsByUser(User dbUser) {
		// TODO Auto-generated method stub
		return permissionDAO.queryPermissionsByUser(dbUser);
	}
	
	
	
	
}
