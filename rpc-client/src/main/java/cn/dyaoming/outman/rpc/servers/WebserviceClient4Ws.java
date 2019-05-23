package cn.dyaoming.outman.rpc.servers;


import cn.dyaoming.outman.webservice.Ws;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Service;


@Service
public class WebserviceClient4Ws {

	/**
	 * 1.代理类工厂的方式,需要拿到对方的接口地址及接口类
	 *
	 * @param args0   调用参数，由于此测试接口是只有一个参数，所以这样处理
	 * @param address 接口地址，由于考虑到接口地址变化的原因，所有将此地址变成参数
	 * @return 返回值， 此接口返还值是json，所以返还字符串
	 */
	public String visit(String args0, String address) {
		try {

			// 代理工厂
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
			// 设置代理地址
			jaxWsProxyFactoryBean.setAddress(address);
			// 设置接口类型
			jaxWsProxyFactoryBean.setServiceClass(Ws.class);
			// 创建一个代理接口实现
			Ws us = (Ws) jaxWsProxyFactoryBean.create();
			// 调用代理接口的方法调用并返回结果
			String result = us.ws001(args0);
			System.out.println("返回结果:" + result);
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
