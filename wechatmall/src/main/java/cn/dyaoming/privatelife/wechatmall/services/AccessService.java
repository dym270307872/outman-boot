package cn.dyaoming.privatelife.wechatmall.services;

import cn.dyaoming.cache.interfaces.CacheInterface;
import cn.dyaoming.models.ApiResult;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.mappers.Sq01Mapper;
import cn.dyaoming.privatelife.wechatmall.models.Sq01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccessService {

    @Autowired
    private Sq01Mapper sq01Mapper;

    @Autowired
    private CacheInterface cacheDao;

    public ApiResult check(String appId){

        Sq01 sq01 = sq01Mapper.getSqInfo(appId);
        DataResult dataResult = new DataResult();
        dataResult.setData(sq01);


        cacheDao.exists("");
        return dataResult;
    }

    @Cacheable("publicInfo")
    public String random(int n){
        return "随机数:"+ new Random().nextInt(n);
    }
}
