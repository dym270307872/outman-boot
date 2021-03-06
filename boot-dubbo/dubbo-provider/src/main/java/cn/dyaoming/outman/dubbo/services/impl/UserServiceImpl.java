package cn.dyaoming.outman.dubbo.services.impl;

import cn.dyaoming.outman.dubbo.beans.UserInfo;
import cn.dyaoming.outman.dubbo.services.UserService;
import org.apache.dubbo.config.annotation.Service;

@Service(interfaceName = "userService")
public class UserServiceImpl implements UserService {


    public String sayHello(String name) {
        return "hi :"+name;
    }

    public UserInfo whoAreYou() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("apache");
        userInfo.setCode("001");
        return userInfo;
    }
}
