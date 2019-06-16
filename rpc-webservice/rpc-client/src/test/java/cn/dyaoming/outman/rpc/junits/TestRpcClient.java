package cn.dyaoming.outman.rpc.junits;


import cn.dyaoming.outman.rpc.filters.LoginInterceptor;
import cn.dyaoming.outman.rpc.services.MyWebService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;


public class TestRpcClient extends BaseJunit{

	private String address = "http://10.110.120.70:28080/services/myWs?wsdl";

//	@Test
	public void hehe(){



//		{http://services.rpc.outman.dyaoming.cn/}cxfService
//		String serverName = "myWebService";
		try {

			// 代理工厂
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
			// 设置代理地址
			jaxWsProxyFactoryBean.setAddress(address);
			// 设置接口类型
			jaxWsProxyFactoryBean.setServiceClass(MyWebService.class);
			// 创建一个代理接口实现
			MyWebService us = (MyWebService) jaxWsProxyFactoryBean.create();
			// 调用代理接口的方法调用并返回结果
			String result = us.ws001("123");
			System.out.println("返回结果:" + result);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void xixi(){
		// 创建动态客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

		Client client = dcf.createClient(address);

		// 需要密码的情况需要加上用户名和密码
				 client.getOutInterceptors().add(new LoginInterceptor("root","admin"));
//		 client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
		Object[] objects = new Object[0];
		try {
			// invoke("方法名",参数1,参数2,参数3....);
			objects = client.invoke("ws001","8^");
			System.out.println("返回数据:" + objects[0]);
		} catch(java.lang.Exception e) {
			e.printStackTrace();
		}
	}
}
