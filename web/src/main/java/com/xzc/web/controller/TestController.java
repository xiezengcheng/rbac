package com.xzc.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzc.common.bean.User;
import com.xzc.manager.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/queryAll")
	public Object queryAll() {
		List<User> users = userService.queryAll();
		return users;
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("json")
	public Object json() {
		Map<String,String> map = new HashMap<String, String>();
		map.put("username","tom");
		return map;
	}
	
}
