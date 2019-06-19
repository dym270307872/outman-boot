package cn.dyaoming.outman.dubbo.services.impl;

import cn.dyaoming.outman.dubbo.beans.UserInfo;
import cn.dyaoming.outman.dubbo.services.UserService;
import org.apache.dubbo.config.annotation.Service;


@Service
public class UserServiceImpl implements UserService {


    public String sayHello(String name) {
        return null;
    }

    public UserInfo whoAreYou() {
        return null;
    }
}
