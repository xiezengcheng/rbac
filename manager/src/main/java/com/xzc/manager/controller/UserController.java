package com.xzc.manager.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzc.common.bean.AJAXResult;
import com.xzc.common.bean.Page;
import com.xzc.common.bean.Role;
import com.xzc.common.bean.User;
import com.xzc.manager.service.RoleService;
import com.xzc.manager.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@ResponseBody
	@RequestMapping("/doAssign")
	public Object doAssign( Integer userid, Integer[] unassignroleids ) {
		AJAXResult result = new AJAXResult();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userid", userid);
			paramMap.put("roleids", unassignroleids);
			userService.insertUserRoles(paramMap);
			
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/dounAssign")
	public Object dounAssign( Integer userid, Integer[] assignroleids ) {
		AJAXResult result = new AJAXResult();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userid", userid);
			paramMap.put("roleids",assignroleids);
			userService.deleteUserRoles(paramMap);
			
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	
	@RequestMapping("/assign")
	public String assign(Integer id,Model model) {
		
		User user = userService.queryById(id);
		model.addAttribute("user",user);
		
		List<Role> roles = roleService.queryAll();
		List<Role> unassignRoles = new ArrayList<Role>();
		List<Role> assingedRoles = new ArrayList<Role>();
		//获取关系表的数据
		List<Integer> roleids = userService.queryRoleidsByUserid(id);
		for (Role role : roles) {
			if(roleids.contains(role.getId())) {
				assingedRoles.add(role);
			}else {
				unassignRoles.add(role);
			}
		}
		
		model.addAttribute("unassignRoles",unassignRoles);
		model.addAttribute("assingedRoles",assingedRoles);
	
		return "user/assign";
		
	}
	
	
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes(Integer[] userid) {
		AJAXResult result = new AJAXResult();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userids", userid);
//			System.out.println(userid[0]);
			userService.deleteUsers(map);
			result.setSuccess(true);
		}catch(Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(Integer id) {
		AJAXResult result = new AJAXResult();
		try {
			userService.deleteUserById(id);
			result.setSuccess(true);
		}catch(Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update(User user) {
		AJAXResult result = new AJAXResult();
		try {
				result.setSuccess(true);
				User dbUser = userService.queryById(user.getId());
				List<User> sUser = userService.checkUser(user);
				if(sUser!=null) {
					for (User user2 : sUser) {
						if(user2.getId()!=dbUser.getId()) {
							result.setSuccess(false);
							break;
						}
					}
				}
				if(result.isSuccess()) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					user.setCreatetime(sdf.format(new Date()));
					userService.update(user);
				}
		}catch(Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result; 
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id,Model model) {
		User user =  userService.queryById(id);
		model.addAttribute("user",user);
		return "user/edit";
	}
	
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert(User user) {
		AJAXResult result = new AJAXResult();
		try {
			List<User> dbUser = userService.checkUser(user);
			if(dbUser.size()>0) {
				result.setSuccess(false);
			}else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				user.setUserpswd("123");
				user.setCreatetime(sdf.format(new Date()));
				userService.insertUser(user);
				result.setSuccess(true);
			}
		}catch(Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	
	@RequestMapping("/add")
	public String add() {
		return "user/add";
	}
	
	
	
	/**
	 * 用户首页
	 * @return
	 */
	@RequestMapping("/index1")
	public String index1(@RequestParam(required=false,defaultValue="1")Integer pageno,@RequestParam(required=false,defaultValue="5")Integer pagesize,Model model) {
		

		//分页查询
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start",(pageno-1)*pagesize);
		map.put("size",pagesize);
		
		List<User> users = userService.pageQueryData(map);
		model.addAttribute("users",users);
		model.addAttribute("pageno",pageno);
		int totalno = 0;
		int totalsize = userService.pageQueryCount(map);
		if(totalsize % pagesize ==0) {
			totalno = totalsize / pagesize;
		}else {
			totalno = totalsize / pagesize +1;
		}
		model.addAttribute("totalno", totalno);
		return "user/index";
	}
	@RequestMapping("/index")
	public String index() {
		return "user/index";
	}
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String queryText,@RequestParam(required=false,defaultValue="1")Integer pageno,@RequestParam(required=false,defaultValue="5")Integer pagesize) {
		AJAXResult result = new AJAXResult();
		try {
			//分页查询
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start",(pageno-1)*pagesize);
			map.put("size",pagesize);
			map.put("queryText",queryText);
			List<User> users = userService.pageQueryData(map);
			int totalno = 0;
			int totalsize = userService.pageQueryCount(map);
			if(totalsize % pagesize ==0) {
				totalno = totalsize / pagesize;
			}else {
				totalno = totalsize / pagesize +1;
			}
			//分页对象
			Page<User> userPage = new Page<User>();
			userPage.setDatas(users);
			userPage.setTotalno(totalno);
			userPage.setPageno(pageno);
			userPage.setTotalsize(totalsize);
			result.setSuccess(true);
			result.setData(userPage);
		}catch(Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	
}
