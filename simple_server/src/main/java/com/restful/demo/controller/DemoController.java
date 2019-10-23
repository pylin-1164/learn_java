package com.restful.demo.controller;
import java.util.List;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;

import io.github.yedaxia.apidocs.ApiDoc;

/**
 * 示例接口Demo
 * @author pyl
 * @date 2019/10/22
 */
@Path
public class DemoController {

//    @Inject
//    private UserService userService;

	/**
	 * <h8>1.1.1- 获取用户信息</h8>
	 * @author pyl
	 * @version 1.0
	 * @date 2019/10/22
	 * @param userName 用户名[required]
	 * @return
	 */
	@ApiDoc(value=User.class,method="get",url="/queryUserByName")
	@JSON
	@GetRoute("queryUserByName")
	public User queryUserByName(@Param String userName){
		User user = new User(userName);
		return user;
	}
	
	/**
	 * <h8>1.1.2- 获取用户列表</h8>
	 * @author pyl
	 * @version 1.0
	 * @date 2019/10/22
	 * @param pageNum 页码[required]
	 * @return
	 */
	@ApiDoc(value=User[].class,method="post",url="/queryPageUser")
	@JSON
	@GetRoute("queryPageUser")
	public List<User> queryPageUser(@Param int pageNum){
		return null;
	}
}