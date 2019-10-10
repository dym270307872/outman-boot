package cn.dyaoming.outman.test;

import junit.BaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

public class JedisClusterRun extends BaseJunit {

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void testJedis(){

//       Jedis jedis = jedisCluster.getResource();

        System.out.println(jedisCluster.exists("hello"));
        System.out.println(jedisCluster.set("hello","hello"));
        System.out.println(jedisCluster.exists("hello"));
        System.out.println(jedisCluster.setnx("hello","hello1"));
        System.out.println(jedisCluster.exists("hello"));
        System.out.println(jedisCluster.get("hello"));

    }
}
