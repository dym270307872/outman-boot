//package cn.dyaoming.outman.service;
//
//import cn.dyaoming.log.entitys.TestLog;
//import cn.dyaoming.log.services.TestLogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisSentinelPool;
//
//import java.util.Date;
//
//@Service
//public class ThreadService {
//
//    @Autowired
//    private JedisSentinelPool jedisSentinelPool;
//
//
//    @Autowired
//    private TestLogService testLogService;
//
//    public void run(String key, String value) {
//        TestLog testLog = new TestLog();
//        testLog.setDataFrom("sentinel");
//        testLog.setBeginTime(new Date());
//        Jedis jedis = jedisSentinelPool.getResource();
//        jedis.set(key, value);
//        jedis.close();
//        testLog.setEndTime(new Date());
//        testLog.setSkey(key);
//        testLog.setSlength(Long.valueOf("" + value.length()));
//        testLog.setTime(testLog.getEndTime().getTime() - testLog.getBeginTime().getTime());
//        testLogService.save(testLog);
//    }
//
//}
