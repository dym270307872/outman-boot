package cn.dyaoming.outman.rpc.services.impl;


import java.util.Random;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

import cn.dyaoming.outman.rpc.services.MyWebService;

@Component("myWebService")
@WebService(name="myWebService",serviceName="myWebService",targetNamespace = "http://services.rpc.outman.dyaoming.cn/", endpointInterface = "cn.dyaoming.outman.rpc.services.MyWebService")
public class MyWebServiceImpl implements MyWebService {

	@Override
	public String ws001(String args0) {
		// TODO Auto-generated method stub
		return "返回结果：" + new Random().nextInt(100);
	}

}
