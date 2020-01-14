package cn.dyaoming.outman.service;

import cn.dyaoming.log.entitys.TestLog;
import cn.dyaoming.log.services.TestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.Date;

@Service
public class ThreadService {

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private TestLogService testLogService;

    public void run(String key, String value) {
        TestLog testLog = new TestLog();
        testLog.setDataFrom("cluster");
        testLog.setBeginTime(new Date());

        jedisCluster.set(key, value);
        try {
            jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        testLog.setEndTime(new Date());
        testLog.setSkey(key);
        testLog.setSlength(Long.valueOf("" + value.length()));
        testLog.setTime(testLog.getEndTime().getTime() - testLog.getBeginTime().getTime());
        testLogService.save(testLog);
    }

}
