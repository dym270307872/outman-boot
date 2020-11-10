package cn.dyaoming.outman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


/**
 * 
 * @author dyaoming
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.dyaoming")
public class JedisApplication {

  public static void main(String[] args) {
    SpringApplication.run(JedisApplication.class, args);
  }

}
