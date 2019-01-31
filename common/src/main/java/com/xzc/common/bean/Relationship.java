/**
 * 
 */
package com.xzc.common.bean;

/**
 * @author xiezengcheng
 * @version 2019年1月30日 下午8:47:01
 */
public class Relationship {

	private Integer roleid;
	private Integer permissionid;
	public Relationship(Integer roleid, Integer permissionid) {
		super();
		this.roleid = roleid;
		this.permissionid = permissionid;
	}
	public Relationship() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getPermissionid() {
		return permissionid;
	}
	public void setPermissionid(Integer permissionid) {
		this.permissionid = permissionid;
	}

	
}
