package com.xzc.manager.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.xzc.common.bean.Relationship;
import com.xzc.common.bean.Role;


public interface RoleService {
	List<Role> pageQueryData(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	List<Role> queryAll();

	void insertRolePermission(Map<String, Object> paramMap);

	void deleteRoleById(Integer id);

	void deleteRoles(Map<String, Object> map);

	Role queryById(Integer id);

	void insertRole(Role role);

	List<Role> checkRole(Role role);

	void update(Role role);

	List<Relationship> getAllRelationship();


}
