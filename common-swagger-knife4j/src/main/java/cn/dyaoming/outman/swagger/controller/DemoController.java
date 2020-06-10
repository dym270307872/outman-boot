package cn.dyaoming.outman.swagger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/demo")
	public String demo() {
		return "success";
	}
	
	
}
