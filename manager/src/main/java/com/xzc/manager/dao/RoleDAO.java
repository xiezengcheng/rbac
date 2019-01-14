package com.xzc.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xzc.common.bean.Role;

public interface RoleDAO {

	List<Role> pageQueryData(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	@Select("select * from role")
	List<Role> queryAll();

	void insertRolePermission(Map<String, Object> paramMap);

	void deleteRolePermissions(Map<String, Object> paramMap);

	void deleteRoleById(Integer id);

	void deleteRoles(Map<String, Object> map);
	
	@Select("select * from role where id=#{id}")
	Role queryById(Integer id);

	void insertRole(Role role);
	
	@Select("select * from role where name=#{name}")
	List<Role> checkRole(Role role);

	void update(Role role);

}
