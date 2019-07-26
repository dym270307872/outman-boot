package cn.dyaoming.privatelife.wechatmall.services;


import cn.dyaoming.cache.interfaces.CacheInterface;
import cn.dyaoming.errors.AppServiceException;
import cn.dyaoming.privatelife.wechatmall.models.Sq01;
import cn.dyaoming.privatelife.wechatmall.models.WeChatAccess;
import cn.dyaoming.privatelife.wechatmall.servers.WeChatServer;
import cn.dyaoming.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class BaseService {

    @Autowired
    protected CacheInterface cacheDao;
    @Autowired
    protected WeChatServer weChatServer;


    protected boolean checkSession(String openId) {
        return cacheDao.exists("cache:session:" + openId);
    }

    protected String getSqId(String openId){
        return StringUtil.processNullString((cacheDao.getCacheTData("cache:session:" + openId,Map.class).get("sqa001")));
    }

    protected String getDecryptParam(String openId,String param){
        return param;
    }

}
