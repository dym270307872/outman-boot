package cn.dyaoming.outman.dubbo.constrollers;

import cn.dyaoming.outman.dubbo.servers.UserServer;
import cn.dyaoming.outman.dubbo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServer userServer;

    @Autowired
    private UserService hessianService;

    @RequestMapping("/getName")
    public String getName(String code) {
        return userServer.getName(code);
    }

    @RequestMapping("/sayHello")
    public String sayHello(String name) {
        return userServer.sayHello(name);
    }

    @RequestMapping("/getName2")
    public String getName2(String code) {
        return hessianService.sayHello(code);
    }
}
