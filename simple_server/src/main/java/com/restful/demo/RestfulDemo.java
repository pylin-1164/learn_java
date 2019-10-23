package com.restful.demo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blade.Blade;

public class RestfulDemo {

	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(RestfulDemo.class);
		
//		Blade.of().get("/", ctx -> ctx.text("Hello Blade")).start();
		Blade.of()
		.get("/hello", ((request, response) -> {
			response.text("Hello World.");
		}))
		.post("/api/sendmsg",((request, response) -> {
			String body = request.bodyToString();
			body = body.replaceAll("%2b", "+").replace("%3d", "=");
			System.out.println(AESUtil.aesDecrypt(body));
			response.text("success");
		})).post("/Auth/loginAuth", ((request,response)->{
			logger.info("request parameter : "+request.query("parameter", ""));
			logger.info("1.1.1 认证接口 > 首次用户登录时做认证，返回用户token");
			response.text("{\"authstatus\":\"1\",\"token\":\""+UUID.randomUUID().toString()+"\",\"userid\":\"1\"}");
		})).post("/Authorize/queryAccessRights", ((request,response)->{
			logger.info("request parameter : "+request.query("parameter", ""));
			logger.info("1.1.2 接入代理鉴权接口 > 请求的url已录入系统，做url鉴权");
			response.text("{\"apptoken\":\""+UUID.randomUUID().toString()+"\",\"verifystatus\":\"1\"}");
		})).post("/Authorize/queryApiRights", ((request,response)->{
			logger.info("2.1.3.1 API代理获取应用token > 访问的url属于API代理应用下的url，根据token获取应用token");
			response.text("{\"apptoken\":\""+UUID.randomUUID().toString()+"\",\"verifystatus\":\"1\"}");
		})).post("/Auth/sepAppApiVerifyToken", (request,response)->{
//			System.out.println(request.attribute("parameter").toString());
			logger.info("2.1.3.2 API后置资源鉴权 > api代理访问已录入的url时，根据apitoken和url鉴权");
			response.text("{\"username\":\"zhangsan\",\"verifystatus\":\"1\"}");
		}).post("/Auth/queryUserByToken", (request,response)->{
			logger.info("2.1.1.5 获取应用最后会话时间 > 控制服务上报应用最后访问时间");
			response.text("{\"retstatus\":\"1\"}");
			
		}).start();
	}
	
}
