package cn.dyaoming.outman.dubbo.services;

import cn.dyaoming.outman.dubbo.beans.UserInfo;

public interface UserService {

	public String sayHello(String name);
	
	public UserInfo whoAreYou();
	
}
