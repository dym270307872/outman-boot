package cn.dyaoming.boot.test;

import junit.BaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class JedisTemplateRun extends BaseJunit {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testJedis(){

        System.out.println(redisTemplate.opsForValue().size("hello")>0L);
        redisTemplate.opsForValue().set("hello","hello");
        System.out.println(redisTemplate.opsForValue().size("hello")>0L);
        redisTemplate.opsForValue().setIfAbsent("hello","hello1");
        System.out.println(redisTemplate.opsForValue().size("hello")>0L);
        System.out.println(redisTemplate.opsForValue().get("hello"));

    }
}
