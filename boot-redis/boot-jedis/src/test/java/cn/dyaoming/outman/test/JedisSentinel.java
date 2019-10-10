package cn.dyaoming.outman.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class JedisSentinel {

    public static void main(String[] args) {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxWaitMillis(1000);

        String masterName = "mymaster";
        Set<String> sentinelSet = new HashSet<>();
        sentinelSet.add("192.168.235.1:26397");
//        sentinelSet.add("127.0.0.1:26380");
//        sentinelSet.add("127.0.0.1:26381");
//        JedisSentinelPool pool = new JedisSentinelPool(masterName, sentinelSet, config);
        JedisSentinelPool pool = new JedisSentinelPool(masterName, sentinelSet,"master");
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String value = jedis.get("hello");
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

}