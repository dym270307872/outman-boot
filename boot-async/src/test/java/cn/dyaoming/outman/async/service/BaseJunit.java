package cn.dyaoming.outman.async.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.dyaoming.outman.async.AsyncApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AsyncApplication.class)
public class BaseJunit {

    protected static final Logger LOGGER  = LogManager.getLogger(BaseJunit.class);


    @Before
    public void before(){
        LOGGER.debug("测试运行开始");
    }

    @After
    public void after(){
        LOGGER.debug("测试运行结束");
    }
}
