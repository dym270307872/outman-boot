package cn.dyaoming.outman.security.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class DemoController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }



    @GetMapping("/admin/hello")
    public String admin() {
        return "hello admin";
    }



    @GetMapping("/user/hello")
    public String user() {
        return "hello user";
    }
}
