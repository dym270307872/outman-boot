package cn.dyaoming.outman.rpc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dyaoming.outman.rpc.servers.WebserviceClient4Ws;


@RestController
@RequestMapping("/")
public class DemoController {

	@Autowired
	private WebserviceClient4Ws ws;



	@RequestMapping("/{serviceName}")
	public String visit(@PathVariable("serviceName") String serviceName, String args0) {
		if ("myWs".equals(serviceName)) {

			return ws.ws001(args0);
		} else {
			return "无此服务";
		}
	}

}
