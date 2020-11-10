package cn.dyaoming.outman;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>启动类</p>
 * 
 * @author DYAOMING
 * @since 2019-04-26
 * @version V1.0
 */
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = { "cn.dyaoming.outman" })
public class Bootstrap {

	/**
	 * <p>主程序入口</p>
	 * @param args 主程序入参
	 */
	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}
	
	
	/**
     * 使用类路径命名空间，使得proguard混淆后，spring能区分实例
     */
//    public static class CustomGenerator implements BeanNameGenerator {
//        @Override
//        public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
//            return definition.getBeanClassName();
//        }
//    }
//
//    public static void main(String[] args) {
//        new SpringApplicationBuilder(Bootstrap.class)
//                .beanNameGenerator(new CustomGenerator())
//                .run(args);
//    }
}
