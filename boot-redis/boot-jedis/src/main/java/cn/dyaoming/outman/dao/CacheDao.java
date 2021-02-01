package cn.dyaoming.outman.dao;

import org.springframework.stereotype.Component;

import cn.dyaoming.cache.interfaces.CacheInterface;
import cn.dyaoming.cache.jedispool.RedisRegexImp;

@Component("cacheDao")
public class CacheDao extends RedisRegexImp implements CacheInterface{

}
