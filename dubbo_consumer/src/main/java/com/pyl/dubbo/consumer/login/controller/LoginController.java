package com.pyl.dubbo.consumer.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pyl.dubbo.api.demo.DemoApi;
import com.pyl.dubbo.consumer.frame.BaseController;

@Controller
public class LoginController extends BaseController{

	@Autowired 
	private DemoApi demoApi;
	
	@RequestMapping("/")
	public String login(){
		setAttribuate("msg", demoApi.demo());
		return "consumer";
	}
}
