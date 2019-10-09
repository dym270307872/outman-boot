package cn.dyaoming.log.services;

import cn.dyaoming.log.daos.TestLogDao;
import cn.dyaoming.log.entitys.TestLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试日志业务类
 */
@Service
public class TestLogService {


    @Autowired
    private TestLogDao testLogDao;


    public void save(TestLog testLog){
        testLogDao.save(testLog);
    }

}
