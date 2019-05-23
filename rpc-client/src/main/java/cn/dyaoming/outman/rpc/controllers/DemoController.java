package cn.dyaoming.outman.rpc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dyaoming.outman.rpc.servers.WebserviceClient4Ws;


@RestController
@RequestMapping("/")
public class DemoController {

	@Autowired
	private WebserviceClient4Ws ws;



	@RequestMapping("/wsService")
	public String visit(String args0, @Value("${config.systemcontant.url}") String url) {
		return ws.visit(args0, url);
	}

}
