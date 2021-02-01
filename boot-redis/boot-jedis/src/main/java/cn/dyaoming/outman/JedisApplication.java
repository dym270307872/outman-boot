package cn.dyaoming.outman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import cn.dyaoming.cache.annotation.EnableCommonCache;



/**
 * 
 * @author dyaoming
 */
@EnableCommonCache
@SpringBootApplication
@ComponentScan(basePackages = "cn.dyaoming")
public class JedisApplication {

  public static void main(String[] args) {
    SpringApplication.run(JedisApplication.class, args);
  }

}
