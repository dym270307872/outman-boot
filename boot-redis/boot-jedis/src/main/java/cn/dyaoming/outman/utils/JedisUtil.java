package cn.dyaoming.outman.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class JedisUtil {

    private final static JedisSentinelPool jedisPool = getPool();

    private static JedisSentinelPool getPool() {
        GenericObjectPoolConfig PoolConfig = new GenericObjectPoolConfig();
        PoolConfig.setMaxIdle(100);
        PoolConfig.setMaxWaitMillis(1000);
        PoolConfig.setMaxTotal(100);
        PoolConfig.setMinIdle(1);

        String[] serverArray = "127.0.0.1:26394".split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
        Set<String> nodes = new HashSet<String>();

        for (String ipPort : serverArray) {
            if (!ipPort.trim().isEmpty()) {
                nodes.add(ipPort);
            }
        }

        JedisSentinelPool jedisSentinePool = new JedisSentinelPool("mymaster", nodes, PoolConfig, 2000);

        return jedisSentinePool;
    }


    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
