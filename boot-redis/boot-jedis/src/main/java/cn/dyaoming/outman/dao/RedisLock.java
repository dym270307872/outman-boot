package cn.dyaoming.outman.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Collections;

@Service
public class RedisLock {

    @Autowired
    private JedisSentinelPool jedisPool;


    public boolean tryLock(String key, String seqid) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            String result = jedis.set(key, seqid, "NX", "PX", 2000);

            if ("OK".equals(result)) {
                return true;
            }
            String oldValue = jedis.get(key);
            if (seqid.equals(oldValue)) {
                return true;
            }

        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public boolean lock(String key, String seqid) {
        final long timeStart = System.currentTimeMillis();
        final long waitTimes = 1000 * 2;
        try {
            while (!tryLock(key, seqid)) {
                Thread.sleep(200);

                long now = System.currentTimeMillis();
                if (now - timeStart > waitTimes) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean unLock(String key, String seqid) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(seqid));
            if ("1L".equals(result)) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }
        return false;
    }

}
