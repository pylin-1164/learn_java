package com.restful.demo.controller;

import java.io.Serializable;

public class UserPro implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String 	idcard;//身份证
	
	private String 	address;//家庭地址
	
	private int 	age;//年龄

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
