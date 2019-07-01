package cn.dyaoming.privatelife.wechatmall.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(String name){
        return "hello "+ name;
    }
}
