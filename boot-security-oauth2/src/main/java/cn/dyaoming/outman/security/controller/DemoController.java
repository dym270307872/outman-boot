package cn.dyaoming.outman.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
