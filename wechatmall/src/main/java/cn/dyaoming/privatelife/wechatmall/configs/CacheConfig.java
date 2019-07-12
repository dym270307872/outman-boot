package cn.dyaoming.privatelife.wechatmall.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations= {"classpath:spring/spring-cache.xml"})
public class CacheConfig {

}
 
