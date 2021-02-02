package cn.dyaoming.outman.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import cn.dyaoming.outman.security.service.UserAuthService;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private UserAuthService userAuthService;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName=authentication.getName();
        String password=(String)authentication.getCredentials();
        
        System.out.println(userName);
        System.out.println(password);
        // TODO Auto-generated method stub
        if(userAuthService.checkLogin(userName,password)) {
            //权限部分，必须传递，不然系统会认为验证失败。
            return new UsernamePasswordAuthenticationToken(userName,password,null);
        }
        throw new BadCredentialsException("密码错误");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

}
