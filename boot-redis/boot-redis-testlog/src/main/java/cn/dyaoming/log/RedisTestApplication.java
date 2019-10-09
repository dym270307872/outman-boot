package cn.dyaoming.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@SpringBootApplication
@ComponentScan(basePackages = {"cn.dyaoming"})
public class RedisTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(RedisTestApplication.class, args);
  }

}
