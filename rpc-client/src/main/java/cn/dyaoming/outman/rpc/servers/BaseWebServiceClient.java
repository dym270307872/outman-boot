package cn.dyaoming.outman.rpc.servers;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


public class BaseWebServiceClient {

	/**
	 * 2：动态调用
	 */
	public static void main2() {
		// 创建动态客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

		Client client = dcf
				.createClient("http://localhost:7001/webService?wsdl");

		// 需要密码的情况需要加上用户名和密码
		// client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
		Object[] objects = new Object[0];
		try {
			// invoke("方法名",参数1,参数2,参数3....);
			objects = client.invoke("wsSSN999",
					"88888-80586||^");
			System.out.println("返回数据:" + objects[0]);
		} catch(java.lang.Exception e) {
			e.printStackTrace();
		}
	}
}
