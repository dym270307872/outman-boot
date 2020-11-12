package cn.dyaoming.outman.security.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
public class SecurityController {
 
    @GetMapping(value = {"/home","/"})
    public String home(){
        return "home";
    }
 
    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }
 
    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }
}