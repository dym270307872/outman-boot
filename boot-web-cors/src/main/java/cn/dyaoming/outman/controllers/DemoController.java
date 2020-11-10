package cn.dyaoming.outman.controllers;


import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/demo")
@RestController
public class DemoController {

	
	@RequestMapping("/testGet")
	public String testGet(String name) {
		return "hello " + name;
	}
	
	
	@RequestMapping("/testPost")
	public String testPost(String name) {
		return "hello " + name;
	}
	
	private static List<String> hehe = new ArrayList<>();
	
	@RequestMapping("/toGc")
	public String toGc(String name) throws Exception {
		for(int i=0;i<10000;i++) {
			
			Thread.sleep(10);
			
			hehe.add(name);
		}
		return "hello " + name;
	}

}
