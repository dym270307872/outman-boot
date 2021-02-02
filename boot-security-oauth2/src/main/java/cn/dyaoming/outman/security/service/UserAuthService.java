package cn.dyaoming.outman.security.service;

import org.springframework.stereotype.Component;

@Component
public class UserAuthService {

    public boolean checkLogin(String userName,String password) {
        if("SuperAdmin".equals(userName)&&"123456".equals(password))
        return true;
        return false;
    }
}
