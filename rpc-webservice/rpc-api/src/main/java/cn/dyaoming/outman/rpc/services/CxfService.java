package cn.dyaoming.outman.rpc.services;

import javax.jws.WebService;

import cn.dyaoming.outman.rpc.models.ApiModel;

@WebService
public interface CxfService {

	
	public String getName();
	
	
	public ApiModel run();
	
	
}
