package cn.dyaoming.outman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dyaoming.outman.service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;
    
    @RequestMapping("noException")
    public String noException() {
        return demoService.noException();
    }
    
    
    @RequestMapping("hasException")
    public String hasException() {
        return demoService.hasException();
    }
    
    
    @RequestMapping("hasExceptionButCatch")
    public String hasExceptionButCatch() {
        return demoService.hasExceptionButCatch();
    }
}
