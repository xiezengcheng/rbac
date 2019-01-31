package com.xzc.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzc.common.bean.AJAXResult;
import com.xzc.common.bean.Permission;
import com.xzc.manager.service.PermissionService;

@Controller
@RequestMapping("/permission")

public class PermissionController {

	@Resource
	private PermissionService permissionService;

	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(Integer id) {
		AJAXResult result = new AJAXResult();
		try {
			permissionService.deletePermission(id);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/update")
	public Object update(Permission permission) {
		AJAXResult result = new AJAXResult();
		try {
			permissionService.updatePermission(permission);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/insert")
	public Object insert(Permission permission) {
		AJAXResult result = new AJAXResult();
		try {
			permissionService.insertPermission(permission);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping("/add")
	public String add(Integer id) {
		return "permission/add";
	}

	@RequestMapping("/edit")
	public String edit(Integer id, Model model) {

		Permission permission = permissionService.queryById(id);
		model.addAttribute("permission", permission);
		return "permission/edit";
	}

	@RequiresPermissions("permission/index")
	@RequestMapping("/index")
	public String index() {
		return "permission/index";
	}

	@RequestMapping("/loadData")
	@ResponseBody
	public Object loadData() {
		List<Permission> permissions = new ArrayList<Permission>();
		// 读取数据库的数据
		// 获取顶级节点
		/*
		 * Permission root = permissionService.queryRootPermission(); //获取子节点
		 * List<Permission> children =
		 * permissionService.queryChildPermissions(root.getId()); for (Permission
		 * permission : children) { //获取子节点 List<Permission> childPermission =
		 * permissionService.queryChildPermissions(permission.getId());
		 * permission.setChildren(childPermission); }
		 * 
		 * 
		 * root.setChildren(children); permissions.add(root);
		 */
		/*
		 * Permission parent = new Permission(); parent.setPid(0); parent.setId(0);
		 * this.queryChildPermissions(parent);
		 * 
		 * return parent.getChildren();
		 */

		// 不用递归 提高效率
		List<Permission> ps = permissionService.queryAll();
		/*
		 * for (Permission p : ps) { Permission child = p; if(p.getPid()==0) {
		 * permissions.add(p); }else { for (Permission permission : ps) {
		 * if(permission.getId().equals(child.getPid())) { Permission parent =
		 * permission; parent.getChildren().add(child); break; } } } }
		 */
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		for (Permission p : ps) {
			permissionMap.put(p.getId(), p);
		}
		for (Permission p : ps) {
			Permission child = p;
			if (child.getPid() == 0) {
				permissions.add(p);
			} else {
				Permission parent = permissionMap.get(child.getPid());
				parent.getChildren().add(child);
			}

		}

		return permissions;
	}

	@RequestMapping("/loadAssignData")
	@ResponseBody
	public Object loadAssignData(Integer roleid) {
		List<Permission> permissions = new ArrayList<Permission>();
		List<Permission> ps = permissionService.queryAll();
	
		//获取当前角色已经分配的许可信息
		List<Integer> permissionids = permissionService.queryPermissionidsByRoleid(roleid);
		
		
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		for (Permission p : ps) {
			if(permissionids.contains(p.getId())){
				p.setChecked(true);
			}else {
				p.setChecked(false);
			}
			permissionMap.put(p.getId(), p);
		}
		for (Permission p : ps) {
			Permission child = p;
			if (child.getPid() == 0) {
				permissions.add(p);
			} else {
				Permission parent = permissionMap.get(child.getPid());
				parent.getChildren().add(child);
			}

		}

		return permissions;

	}

	// 递归查询许可数据
	private void queryChildPermissions(Permission parent) {
		// 获取子节点
		List<Permission> children = permissionService.queryChildPermissions(parent.getId());
		for (Permission permission : children) {
			this.queryChildPermissions(permission);
		}
		parent.setChildren(children);

	}

}
