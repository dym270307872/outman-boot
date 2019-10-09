package cn.dyaoming.log.constrollers;

import cn.dyaoming.log.entitys.TestLog;
import cn.dyaoming.log.services.TestLogService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logApi")
public class TestLogConstroller {

    @Autowired
    private TestLogService testLogService;

    @RequestMapping("/save")
    public String save(String testLog){

        TestLog testLog1 = JSON.parseObject(testLog,TestLog.class);

        testLogService.save(testLog1);
        return "true";
    }
}
