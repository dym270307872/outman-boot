package cn.dyaoming.outman.docker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("hello")
    public String helloDocker(){
        return "hello Docker";
    }
}
