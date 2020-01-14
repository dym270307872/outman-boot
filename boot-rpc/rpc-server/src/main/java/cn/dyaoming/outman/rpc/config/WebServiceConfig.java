package cn.dyaoming.outman.rpc.config;


import cn.dyaoming.outman.rpc.filters.AuthInterceptor;
import cn.dyaoming.outman.rpc.services.CxfService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.dyaoming.outman.rpc.services.MyWebService;

import javax.xml.ws.Endpoint;


@Configuration
public class WebServiceConfig {

	@Autowired
	private Bus bus;

//	@Autowired
//	private AuthInterceptor interceptor;

	/*jax-ws*/
	@Bean
	public Endpoint myWs(MyWebService myWebService) {
		EndpointImpl endpoint = new EndpointImpl(bus, myWebService);
		endpoint.publish("/myWs");
//		endpoint.getInInterceptors().add(interceptor); //TODO 对此服务进行用户认证验证
		return endpoint;
	}


	@Bean
	public Endpoint myCxfWs(CxfService cxfService) {
		EndpointImpl endpoint = new EndpointImpl(bus, cxfService);
		endpoint.publish("/myCxfWs");
		return endpoint;
	}

}