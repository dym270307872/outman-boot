package cn.dyaoming.outman.kafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

	 @Autowired
	    private KafkaTemplate<String, String> kafkaTemplate;
	
	@RequestMapping("/test")
	public String test(String value) {
		
		
		kafkaTemplate.send("test_topic", value);
		return "success";
	}
}
