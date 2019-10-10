package cn.dyaoming.outman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:spring boot 启动类
 */
@Configuration
@SpringBootApplication
@EnableRedisRepositories
//@EnableCaching
@ComponentScan(basePackages = {"cn.dyaoming"})
public class RedisTemplateApplication {

  public static void main(String[] args) {
    SpringApplication.run(RedisTemplateApplication.class, args);
  }

}
