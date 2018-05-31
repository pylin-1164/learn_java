package com.pyl.dubbo.provide.demo;

import com.pyl.dubbo.api.demo.DemoApi;

public class DemoProvide implements DemoApi{

	public String demo() {
		return "dubbo demo api success Master";
	}

}
