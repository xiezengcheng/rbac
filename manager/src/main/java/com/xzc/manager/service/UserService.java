package com.xzc.manager.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xzc.common.bean.User;

public interface UserService {

	List<User> queryAll();

	User query4Login(User user);
	
	User queryLogin(String loginacct);

	List<User> pageQueryData(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	void insertUser(User user);

	List<User> checkUser(User user);

	void update(User user);

	User queryById(Integer id);

	void deleteUserById(Integer id);

	void deleteUsers(Map<String, Object> map);

	void insertUserRoles(Map<String, Object> paramMap);

	void deleteUserRoles(Map<String, Object> paramMap);

	List<Integer> queryRoleidsByUserid(Integer id);

	Collection<String> queryRolesByUserid(Integer id);

}
