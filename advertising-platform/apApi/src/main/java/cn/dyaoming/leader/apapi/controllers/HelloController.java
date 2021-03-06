package cn.dyaoming.leader.apapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p>hello演示控制器</p>
 * 
 * @author DYAOMING
 * @since 2019-05-12
 * @version V1.0
 */
@Controller
public class HelloController {
    
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }


    @RequestMapping("/ap000")
    public String ap000(ModelMap map) {
//        map.addAttribute("host", "http://blog.didispace.com");
        return "ap000";
    }

}