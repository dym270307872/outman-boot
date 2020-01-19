package cn.dyaoming.outman.kafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dyaoming.outman.kafka.servers.KafkaSender;

@RestController
@RequestMapping("/demo")
public class DemoController {

	 @Autowired
	 private KafkaSender kafkaSender;
	
	@RequestMapping("/test")
	public String test(String value) {

		kafkaSender.send();
		return "success";
	}


}
