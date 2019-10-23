package com.restful.demo.controller;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	/**用户名*/
	private String username;
	
	private String cnname;		//中文名
	
	private int sex;//性别【0:男，1：女】
	
	private UserPro userPro;//用户属性
	
	public User() {

	}
	
	public User(String username){
		this.username = username;
		this.cnname = username;
		this.sex = 0;
		this.userPro = new UserPro();
		this.userPro.setAddress("test");
		this.userPro.setAge(20);
		this.userPro.setIdcard(UUID.randomUUID().toString());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public UserPro getUserPro() {
		return userPro;
	}

	public void setUserPro(UserPro userPro) {
		this.userPro = userPro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
