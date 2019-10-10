package cn.dyaoming.outman.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class RandomService {

    @Cacheable(value = "userInfo")
    public String getRandom(int max){
        return "返回值：" + new Random().nextInt(max);
    }
    
    @Cacheable(value = "publicInfo")
    public String getRandom2(int max){
        return "返回值：" + new Random().nextInt(max);
    }
    
    
    
    @CachePut(value = "userInfo",key="#name +'to userinfo'")
    public String find(String name){
        return "用户名：" + name;
    }
    
    @CacheEvict(value = "userInfo",key="#name +'to userinfo'")
    public void delete(String name){
//        return "用户名：" + name;
    }
}
