package cn.dyaoming.outman.dubbo;

import cn.dyaoming.outman.dubbo.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    //发布服务
    @Bean(name = "/hessianService")
    public HessianServiceExporter accountService(UserService hessianService) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(hessianService);
        exporter.setServiceInterface(UserService.class);
        return exporter;
    }
}
