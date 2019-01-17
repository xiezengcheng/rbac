package com.xzc.web.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzc.common.bean.AJAXResult;
import com.xzc.common.bean.Permission;
import com.xzc.common.bean.User;
import com.xzc.common.utils.MD5Util;
import com.xzc.manager.service.PermissionService;
import com.xzc.manager.service.UserService;

@Controller
public class DispatcherController {
	
	@Resource
	private UserService userService;
	@Resource
	private PermissionService permissionService;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	
	/**
	 * 执行登录
	 * @return
	 */
	@RequestMapping("/doLogin")
	public String doLogin(User user,Model model) {
		//实体类获取表单数据
		User dbUser = userService.query4Login(user);
		if(dbUser!=null) {
			//登录成功.跳转到主页面
			return "main";
	
		}else {
			//错误信息
			String errorMsg = "登录账号或密码不正确";
			model.addAttribute("errorMsg",errorMsg);
			//登录失败跳转回登录页面
			
			return "redirect:/login";
		}
	}
	
	@ResponseBody
	@RequestMapping("/doAJAXLogin")
	public Object doAJAXLogin(User user,HttpSession session) {
		AJAXResult result = new AJAXResult();
		user.setUserpswd(MD5Util.digest(user.getUserpswd()));
		//实体类获取表单数据
		User dbUser = userService.query4Login(user);
		if(dbUser!=null) {
			session.setAttribute("loginUser",dbUser);
			//获取用户权限信息
			List<Permission> permissions = permissionService.queryPermissionsByUser(dbUser);
			Permission root = null;
			Map<Integer,Permission> permissionMap = new HashMap<Integer,Permission>();
			Set<String> uriSet = new HashSet<String>();
			
			for (Permission permission : permissions) {
				permissionMap.put(permission.getId(),permission);
				if(permission.getUrl()!=null&&!"".equals(permission.getUrl())) {
					uriSet.add(session.getServletContext().getContextPath()+permission.getUrl());
				}
				
			}
			session.setAttribute("authUriSet", uriSet);
			
			for (Permission permission : permissions) {
				Permission child = permission;
				if(child.getPid()==0) {
					root = permission;
				}else {
					Permission parent = permissionMap.get(child.getPid());
					parent.getChildren().add(child);	
				}
				
			}
			session.setAttribute("rootPermission",root);
			//登录成功
			result.setSuccess(true);
	
		}else {
			result.setSuccess(false);
		}
		return result;
	}
	
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//session.removeAttribute("loginUser");
		//删除所有session数据
		session.invalidate();
		return "redirect:login";
	}
	
	
	
	
	
	
	
	
	
}
