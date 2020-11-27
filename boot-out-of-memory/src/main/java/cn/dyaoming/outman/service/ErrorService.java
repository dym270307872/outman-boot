package cn.dyaoming.outman.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ErrorService {

	private final static List<Object> DA_OBJECTS = new ArrayList<>();

	public String outOfMemory() {

		while (true) {
			byte[] bigDate = new byte[1024];
			DA_OBJECTS.add(bigDate);
			if (false) {
				break;
			}
		}

		return "success";
	}
	
	
	public String eatMemory() {
		List<Object> list = new ArrayList<>();
		for (int i=0;i<100;i++) {
			byte[] bigDate = new byte[1024];
			list.add(bigDate);
		}
		
		return "success";
	}
}
