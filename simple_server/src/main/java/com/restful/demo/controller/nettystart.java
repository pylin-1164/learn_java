package com.restful.demo.controller;

import com.blade.Blade;

public class nettystart {

	public static void main(String[] args) {
		Blade blade = Blade.of();
		blade.scanPackages("com.restful.demo");
		blade.start();
	}
}
