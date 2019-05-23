package cn.dyaoming.outman.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dyaoming.outman.entitys.Temp;
import cn.dyaoming.outman.services.HelloService;


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
public class HelloController {

	@Autowired
	private HelloService helloService;
	
	
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
		return "hello " + name;
	}
	
	
	

	/**
	 * <p>
	 * 欢迎类临时表数据展示
	 * </p>
	 *
	 * @param name 姓名
	 * @return 欢迎语
	 */
	@RequestMapping("/error")
	public List<Temp> error() {
		return helloService.list();
	}

}
