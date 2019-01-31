package com.xzc.manager.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xzc.common.bean.User;

public interface UserDAO {

	@Select("select * from user")
	List<User> queryAll();

	@Select("select * from user where loginacct=#{user.loginacct} and userpswd=#{user.userpswd}")
	User query4Login(@Param("user")User user);
	@Select("select * from user where loginacct=#{loginacct}")
	User queryLogin(String loginacct);

	List<User> pageQueryData(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	void insert(User user);
	@Select("select * from user where loginacct=#{loginacct}")
	List<User> check(User user);

	void update(User user);
	
	@Select("select * from user where id=#{id}")
	User queryById(Integer id);

	
	void delete(Integer id);

	void deleteUsers(Map<String, Object> map);

	void insertUserRoles(Map<String, Object> paramMap);

	void deleteUserRoles(Map<String, Object> paramMap);
	@Select("select roleid from user_role where userid=#{id}")
	List<Integer> queryRoleidsByUserid(Integer id);

	@Select("select name from role where id in(select roleid from user_role where userid = #{id})")
	Collection<String> queryRolesByUserid(Integer id);


}
