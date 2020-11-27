package cn.dyaoming.outman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dyaoming.outman.service.DemoService;

/**
 * @author dym
 *
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private DemoService demoService;
	
	@RequestMapping("/getA")
	public String getA() {
		return demoService.getA();
	}
	
	
}
