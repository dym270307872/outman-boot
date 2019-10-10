package cn.dyaoming.outman.test;

import cn.dyaoming.outman.service.ThreadService;
import junit.BaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class JedisTemplateRun extends BaseJunit {

    @Autowired
    private ThreadService threadService;

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

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                final String key = "key(" + i + "|" + j + ")";
                /*new Thread(){
                    @Override
                    public void run(){*/
                        threadService.run(key, key);
                    /*}
                }.start();*/

            }
        }

    }
}
