package cn.dyaoming.outman.redishttpsession.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dyaoming.outman.redishttpsession.supper.BaseController;


/**
 * <p>
 * 欢迎类
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-04-26
 * @version V1.0
 */
@RequestMapping("/hello")
@RestController
public class HelloController extends BaseController{

	
	
	
	/**
	 * <p>
	 * 欢迎类，展示
	 * </p>
	 *
	 * @param name 姓名
	 * @return 欢迎语
	 */
	@RequestMapping("/hi")
	public String hi(String name) {
		setUserName(name);
		return "hello " + name;
	}
	
	
	
	@RequestMapping("/whoami")
	public String whoami() {
		return "i am " + getUserName();
	}
	
	
	@RequestMapping("/where")
	public String where() {
		return "there is zhengzhou" ;
	}

}
