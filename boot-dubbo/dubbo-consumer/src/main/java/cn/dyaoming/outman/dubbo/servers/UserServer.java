package cn.dyaoming.outman.dubbo.servers;

import cn.dyaoming.outman.dubbo.beans.UserInfo;
import cn.dyaoming.outman.dubbo.services.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component("userServer")
public class UserServer {

    @Reference
    private UserService userService;


    public String getName(String code) {
        UserInfo userInfo = userService.whoAreYou();
        return userInfo.getName();
    }

    public String sayHello(String name) {

        return userService.sayHello(name);
    }

}
