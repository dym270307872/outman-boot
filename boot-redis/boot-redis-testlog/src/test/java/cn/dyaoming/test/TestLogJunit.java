package cn.dyaoming.test;

import cn.dyaoming.log.entitys.TestLog;
import cn.dyaoming.log.services.TestLogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TestLogJunit extends BaseJunit {

    @Autowired
    private TestLogService testLogService;

    @Test
    public void main(){
        TestLog testLog = new TestLog();
        testLog.setBeginTime(new Date());
        testLog.setSkey("hello");
        testLogService.save(testLog);
    }
}
