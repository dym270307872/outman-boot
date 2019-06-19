package cn.dyaoming.outman.dubbo;

import cn.dyaoming.outman.dubbo.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;


@SpringBootApplication
public class Application {

    @Bean
    public HessianProxyFactoryBean helloClient() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl("http://localhost:7011/hessianService");
        factory.setServiceInterface(UserService.class);
        return factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
