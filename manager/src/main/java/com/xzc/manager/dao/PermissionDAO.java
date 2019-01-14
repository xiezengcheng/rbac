package com.xzc.manager.dao;


import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.xzc.common.bean.Permission;
import com.xzc.common.bean.User;

@Repository
public interface PermissionDAO {

	@Select("select * from permission where pid is null")
	public Permission queryRoot();

	@Select("select * from permission where pid=#{pid}")
	public List<Permission> queryChildren(Integer pid);

	@Select("select * from permission")
	public List<Permission> queryAll();

	public void insert(Permission permission);

	@Select("select * from permission where id=#{id}")
	public Permission queryById(Integer id);

	public void update(Permission permission);

	public void delete(Integer id);

	@Select("select permissionid from role_permission where roleid=#{roleid}")
	public List<Integer> queryPermissionsidByRoleid(Integer roleid);

	public List<Permission> queryPermissionsByUser(User dbUser);

	
	
}
