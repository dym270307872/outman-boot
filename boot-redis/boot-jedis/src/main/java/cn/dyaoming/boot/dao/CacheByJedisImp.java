package cn.dyaoming.boot.dao;

import cn.dyaoming.cache.interfaces.CacheInterface;

import cn.dyaoming.errors.AppDaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.Collection;
@Component("cacheDao")
public class CacheByJedisImp  implements CacheInterface
{

    private static final Logger logger  = LogManager.getLogger(CacheByJedisImp.class);

    @Autowired
    private JedisPool jedisPool;

    public boolean exists(Object o) throws AppDaoException {
        jedisPool.getResource().exists(o.toString());
        return false;
    }

    public boolean setCacheObjectData(Object o, Object o1) throws AppDaoException {
        return false;
    }

    public boolean setCacheObjectData(Object o, Object o1, long l) throws AppDaoException {
        return false;
    }

    public boolean setCacheObjectData(Object o, Object o1, boolean b) throws AppDaoException {
        return false;
    }

    public boolean setCacheObjectData(Object o, Object o1, long l, boolean b) throws AppDaoException {
        return false;
    }

    public boolean deleteCacheData(Object o) throws AppDaoException {
        return false;
    }

    public Object getCacheData(Object o) throws AppDaoException {
        return null;
    }

    public <T> T getCacheTData(String s, Class<T> aClass) throws AppDaoException {
        return null;
    }

    public void clear() throws AppDaoException {

    }

    public Collection<String> getKeys(String s) throws AppDaoException {
        return null;
    }

    public boolean deleteRegexCacheData(String s) throws AppDaoException {
        return false;
    }
}
