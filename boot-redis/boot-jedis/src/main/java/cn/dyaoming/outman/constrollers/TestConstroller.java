package cn.dyaoming.outman.constrollers;

import cn.dyaoming.outman.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/test")
public class TestConstroller {

	@Autowired
	private RandomService randomService;

	@RequestMapping("/random")
	public String random(int max) {
		return randomService.getRandom(max);
	}

	@RequestMapping("/random2")
	public String random2(int max) {
		return randomService.getRandom2(max);
	}

	@RequestMapping("/get")
	public String get(String name) {
		return randomService.find(name);
	}

	@RequestMapping("/delete")
	public void delete(String name) {
		randomService.delete(name);
	}
}
