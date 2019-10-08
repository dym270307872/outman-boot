package cn.dyaoming.boot.test;

import cn.dyaoming.boot.utils.JedisUtil;
import junit.BaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class JedisSentinelRun extends BaseJunit {

//    @Autowired
//    private JedisSentinelPool jedisSentinelPool;

    @Test
    public void testJedis(){

       Jedis jedis = JedisUtil.getJedis();

        System.out.println(jedis.exists("hello"));
        System.out.println(jedis.set("hello","hello"));
        System.out.println(jedis.exists("hello"));
        System.out.println(jedis.setnx("hello","hello1"));
        System.out.println(jedis.exists("hello"));
        System.out.println(jedis.get("hello"));

    }
}
