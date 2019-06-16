package cn.dyaoming.outman.config;


import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>用于兼容tomcat9.0以上对url特殊字符控制限制</p>
 * 
 * @author DYAOMING
 * @since 2019-05-23
 * @version V1.0
 */
@Component
public class PortalTomcatWebServerCustomizer implements
		WebServerFactoryCustomizer {

	@Override
	public void customize(WebServerFactory factory) {
		// TODO Auto-generated method stub
		TomcatServletWebServerFactory containerFactory = (TomcatServletWebServerFactory) factory;
		containerFactory.addConnectorCustomizers(new TomcatConnectorCustomizer(){
			@Override
			public void customize(Connector connector) {
				connector.setAttribute("relaxedQueryChars", "[]|{}^\"<>");
				connector.setAttribute("relaxedPathChars", "[]|");
			}
		});
	}
}