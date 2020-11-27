package cn.dyaoming.outman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dyaoming.outman.service.DemoService;
import cn.dyaoming.outman.service.ErrorService;

/**
 * @author dym
 *
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private DemoService demoService;

	@Autowired
	private ErrorService errorService;

	@RequestMapping("/getA")
	public String getA() {
		return demoService.getA();
	}

	@RequestMapping("/outOfMemory")
	public String outOfMemory() {
		return errorService.outOfMemory();
	}
	
	
	@RequestMapping("/eatMemory")
	public String eatMemory() {
		return errorService.eatMemory();
	}
}
