package com.xzc.manager.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzc.common.bean.Role;
import com.xzc.manager.dao.RoleDAO;
import com.xzc.manager.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;


	public List<Role> pageQueryData(Map<String, Object> map) {
		return roleDAO.pageQueryData(map);
	}

	public int pageQueryCount(Map<String, Object> map) {
		return roleDAO.pageQueryCount(map);
	}

	public List<Role> queryAll() {
		return roleDAO.queryAll();
	}

	public void insertRolePermission(Map<String, Object> paramMap) {
		roleDAO.deleteRolePermissions(paramMap);
		roleDAO.insertRolePermission(paramMap);
	}

	public void deleteRoleById(Integer id) {
		roleDAO.deleteRoleById(id);
	}

	public void deleteRoles(Map<String, Object> map) {
		roleDAO.deleteRoles(map);
	}

	public Role queryById(Integer id) {
		return roleDAO.queryById(id);
	}

	public void insertRole(Role role) {
		roleDAO.insertRole(role);
	}

	public List<Role> checkRole(Role role) {
		return roleDAO.checkRole(role);
	}

	public void update(Role role) {
		roleDAO.update(role);
	}

}
