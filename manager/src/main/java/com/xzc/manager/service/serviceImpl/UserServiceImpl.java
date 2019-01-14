package com.xzc.manager.service.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzc.common.bean.User;
import com.xzc.manager.dao.UserDAO;
import com.xzc.manager.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDAO userDAO;

	public List<User> queryAll() {
		return userDAO.queryAll();
	}

	public User query4Login(User user) {
		return userDAO.query4Login(user);
	}

	public List<User> pageQueryData(Map<String, Object> map) {
		return userDAO.pageQueryData(map);
	}

	public int pageQueryCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDAO.pageQueryCount(map);
	}

	public void insertUser(User user) {
		userDAO.insert(user);
	}

	public List<User> checkUser(User user) {
		return userDAO.check(user);
	}

	public void update(User user) {
		userDAO.update(user);
	}

	public User queryById(Integer id) {
		return userDAO.queryById(id);
	}

	public void deleteUserById(Integer id) {
		userDAO.delete(id);
	}

	public void deleteUsers(Map<String, Object> map) {
		userDAO.deleteUsers(map);
	}

	public void insertUserRoles(Map<String, Object> paramMap) {
		userDAO.insertUserRoles(paramMap);
	}

	public void deleteUserRoles(Map<String, Object> paramMap) {
		userDAO.deleteUserRoles(paramMap);
	}

	public List<Integer> queryRoleidsByUserid(Integer id) {
		return userDAO.queryRoleidsByUserid(id);
	}
	
}
