package cn.dyaoming.boot.service;

import cn.dyaoming.log.entitys.TestLog;
import cn.dyaoming.log.services.TestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ThreadService extends Thread {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TestLogService testLogService;


    private String key;
    private String value;


    @Override
    public void run() {
        TestLog testLog = new TestLog();
        testLog.setDataFrom(this.getClass().getName());
        testLog.setBeginTime(new Date());

        redisTemplate.opsForValue().set(key, value);

        testLog.setEndTime(new Date());
        testLog.setSkey(key);
        testLog.setSlength(Long.valueOf("" + key.length()));
        testLogService.save(testLog);
    }

    public void init(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
