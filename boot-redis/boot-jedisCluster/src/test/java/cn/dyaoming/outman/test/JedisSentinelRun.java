package cn.dyaoming.outman.test;

import junit.BaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class JedisSentinelRun extends BaseJunit {

    @Autowired
    private JedisSentinelPool jedisSentinelPool;

    @Test
    public void testJedis(){

       Jedis jedis = jedisSentinelPool.getResource();

        System.out.println(jedis.exists("hello"));
        System.out.println(jedis.set("hello","hello"));
        System.out.println(jedis.exists("hello"));
        System.out.println(jedis.setnx("hello","hello1"));
        System.out.println(jedis.exists("hello"));
        System.out.println(jedis.get("hello"));

    }
}
