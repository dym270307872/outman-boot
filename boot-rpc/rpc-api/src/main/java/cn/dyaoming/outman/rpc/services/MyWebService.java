package cn.dyaoming.outman.rpc.services;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService(name = "myWebService",targetNamespace="http://services.rpc.outman.dyaoming.cn/")
public interface MyWebService
{
	@WebMethod
	public String ws001(String args0);
	
	
}
