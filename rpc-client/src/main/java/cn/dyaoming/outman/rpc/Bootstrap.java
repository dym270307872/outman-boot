package cn.dyaoming.outman.rpc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * <p>
 * 项目启动类
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-05-23
 * @version V1.0
 */
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = { "cn.dyaoming" })
public class Bootstrap {

	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}

}
