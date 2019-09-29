package cn.dyaoming.boot.test;

import junit.BaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisRun extends BaseJunit {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void testJedis(){

       Jedis jedis = jedisPool.getResource();

        System.out.println(jedis.exists("hello"));
        System.out.println(jedis.set("hello","hello"));
        System.out.println(jedis.exists("hello"));
        System.out.println(jedis.setnx("hello","hello1"));
        System.out.println(jedis.exists("hello"));
        System.out.println(jedis.get("hello"));

    }
}
