package com.xzc.common.bean;

public class User {
	
	private Integer id;
	private String username;
	private String loginacct;
	private String userpswd;
	private String email;
	private String createtime;
	
	
	
	public String getCreatetime() {
		return createtime;
	}



	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}



	public User() {
		super();
	}

	
	




	public User(String username, String loginacct, String userpswd, String email, String createtime) {
		super();
		this.username = username;
		this.loginacct = loginacct;
		this.userpswd = userpswd;
		this.email = email;
		this.createtime = createtime;
	}



	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginacct() {
		return loginacct;
	}
	public void setLoginacct(String loginacct) {
		this.loginacct = loginacct;
	}
	public String getUserpswd() {
		return userpswd;
	}
	public void setUserpswd(String userpswd) {
		this.userpswd = userpswd;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", loginacct=" + loginacct + ", userpswd=" + userpswd
				+ ", email=" + email + ", createtime=" + createtime + "]";
	}




	
	
	
	

}
