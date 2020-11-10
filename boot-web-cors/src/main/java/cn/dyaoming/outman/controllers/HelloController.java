package cn.dyaoming.outman.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

}
