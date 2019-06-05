package cn.dyaoming.outman.rpc.services.impl;


import cn.dyaoming.outman.rpc.models.ApiModel;
import cn.dyaoming.outman.rpc.services.CxfService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;


@Component("cxfService")
@WebService(name="cxfService",serviceName="cxfService",targetNamespace = "http://services.rpc.outman.dyaoming.cn/", endpointInterface = "cn.dyaoming.outman.rpc.services.CxfService")

public class MyCxfServiceImpl implements CxfService {

	@Override
	public String getName() {
		return "cxfService";
	}



	@Override
	public ApiModel run() {
		ApiModel apiModel = new ApiModel();
		apiModel.setMessage("成功");
		apiModel.setName("cxfService");
		return apiModel;
	}
}
