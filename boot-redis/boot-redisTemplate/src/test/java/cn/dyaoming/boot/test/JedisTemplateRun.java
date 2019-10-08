package cn.dyaoming.boot.test;

import cn.dyaoming.log.services.TestLogService;
import junit.BaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class JedisTemplateRun extends BaseJunit {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TestLogService testLogService;


    /*@Test
    public void testJedis(){

        System.out.println(redisTemplate.opsForValue().size("hello")>0L);
        redisTemplate.opsForValue().set("hello","hello");
        System.out.println(redisTemplate.opsForValue().size("hello")>0L);
        redisTemplate.opsForValue().setIfAbsent("hello","hello1");
        System.out.println(redisTemplate.opsForValue().size("hello")>0L);
        System.out.println(redisTemplate.opsForValue().get("hello"));

    }*/


    @Test
    public void main() {

        new Thread() {
            @Override
            public void run() {
                try {

                    testLogService.save();

                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
