package cn.dyaoming.outman.dao;

import cn.dyaoming.cache.interfaces.CacheInterface;
import cn.dyaoming.errors.AppDaoException;
import cn.dyaoming.utils.AesUtil;
import cn.dyaoming.utils.SerializeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.Collection;

@Component("cacheDao")
public class CacheByJedisImp implements CacheInterface {

    private static final Logger logger = LogManager.getLogger(CacheByJedisImp.class);

    @Autowired
    private JedisPool jedisPool;


    /**
     * 功能描述：判断是否存在键值。
     * <p/>
     * 创建时间：2017-02-12
     * <p/>
     * 创建人： DYM
     * <p/>
     * 修改人：无
     * <p/>
     * 修改时间：无
     * <p/>
     * 修改备注：无
     *
     * @param key String类型 键
     * @return boolean类型 返回结果
     */
    @Override
    public boolean exists(Object key) throws AppDaoException {
        return jedisPool.getResource().exists(key.toString());
    }


    /**
     * 功能描述：设置缓存对象类型内容。
     * <p/>
     * 创建时间：2017-02-12
     * <p/>
     * 创建人： DYM
     * <p/>
     * 修改人：无
     * <p/>
     * 修改时间：无
     * <p/>
     * 修改备注：无
     *
     * @param key   String类型 键
     * @param value Object类型 内容
     * @return boolean类型 返回结果
     */
    @Override
    public boolean setCacheObjectData(Object key, Object value) throws AppDaoException {
        return this.setCacheObjectData(key, value, DEFALUTTIME, DEFALUTSECRET);
    }


    /**
     * 功能描述：设置缓存对象类型内容。
     * <p/>
     * 创建时间：2017-02-12
     * <p/>
     * 创建人： DYM
     * <p/>
     * 修改人：无
     * <p/>
     * 修改时间：无
     * <p/>
     * 修改备注：无
     *
     * @param key       String类型 键
     * @param value     Object类型 内容
     * @param validTime long类型 有效时间（单位：秒）
     * @return boolean类型 返回结果
     */
    @Override
    public boolean setCacheObjectData(Object key, Object value, final long validTime)
            throws AppDaoException {
        return this.setCacheObjectData(key, value, validTime, DEFALUTSECRET);
    }


    @Override
    public boolean setCacheObjectData(Object key, Object value, boolean secret)
            throws AppDaoException {
        return this.setCacheObjectData(key, value, DEFALUTTIME, secret);
    }


    @Override
    public boolean setCacheObjectData(Object key, Object value, final long validTime,
                                      boolean secret) throws AppDaoException {
        boolean rv = false;
        try {
            if (!StringUtils.isEmpty(key)) {
                final byte[] finalKey = key.toString().getBytes("utf-8");
                byte[] valueByte = SerializeUtil.serialize(value);
                if (secret) {
                    valueByte = AesUtil.encrypt(valueByte);
                    int length_byte = DEFALUTHEAD.length + valueByte.length;
                    byte[] all_byte = new byte[length_byte];

                    System.arraycopy(DEFALUTHEAD, 0, all_byte, 0,
                            DEFALUTHEAD.length);
                    System.arraycopy(valueByte, 0, all_byte, DEFALUTHEAD.length,
                            valueByte.length);
                    valueByte = all_byte;
                }
                final byte[] finalValue = valueByte;
                Jedis jedis = jedisPool.getResource();

                if (validTime > 0L) {
                    int expireTime = new Long(validTime).intValue();
                    jedis.setex(finalKey, expireTime, finalValue);
                } else {
                    jedis.set(finalKey, finalValue);

                }
                return true;
            }
        } catch (Exception e) {
            logger.error("异常：setCacheObjectData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            throw new AppDaoException("缓存对象类型内容出现异常！", e);
        }

        return rv;
    }


    /**
     * 功能描述：删除缓存内容。
     * <p/>
     * 创建时间：2017-02-12
     * <p/>
     * 创建人： DYM
     * <p/>
     * 修改人：无
     * <p/>
     * 修改时间：无
     * <p/>
     * 修改备注：无
     *
     * @param key String类型 键
     * @return boolean类型 返回结果
     */
    @Override
    public boolean deleteCacheData(Object key) throws AppDaoException {
        boolean rv = false;

        try {
            if (!StringUtils.isEmpty(key)) {
                final byte[] finalKey = key.toString().getBytes("utf-8");
                jedisPool.getResource().del(finalKey);
                rv = true;
            }
        } catch (Exception e) {
            logger.error("异常：deleteCacheData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            throw new AppDaoException("删除缓存内容出现异常！", e);
        }

        return rv;
    }


    /**
     * 功能描述：获取缓存内容。
     * <p/>
     * 创建时间：2017-02-12
     * <p/>
     * 创建人： DYM
     * <p/>
     * 修改人：无
     * <p/>
     * 修改时间：无
     * <p/>
     * 修改备注：无
     *
     * @param key String类型 键
     * @return Object类型 返回结果
     */
    @Override
    public Object getCacheData(Object key) throws AppDaoException {
        Object rv = null;

        try {

            if (!StringUtils.isEmpty(key)) {
                final byte[] finalKey = key.toString().getBytes("utf-8");
                byte[] value = jedisPool.getResource().get(finalKey);
                if (value == null) {
                    return null;
                }
                byte[] head = new byte[DEFALUTHEAD.length];
                System.arraycopy(value, 0, head, 0, DEFALUTHEAD.length);
                if (Arrays.equals(head, DEFALUTHEAD)) {
                    byte[] body = new byte[value.length - DEFALUTHEAD.length];
                    System.arraycopy(value, DEFALUTHEAD.length, body, 0,
                            value.length - DEFALUTHEAD.length);
                    body = AesUtil.decrypt(body);
                    return SerializeUtil.unSerialize(body);
                }
                return SerializeUtil.unSerialize(value);
            }
        } catch (Exception e) {
            logger.error("异常：getCacheData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            throw new AppDaoException("获取缓存内容出现异常！", e);
        }

        return rv;
    }


    /**
     * 功能描述：获取缓存内容。
     * <p/>
     * 创建时间：2017-02-12
     * <p/>
     * 创建人： DYM
     * <p/>
     * 修改人：无
     * <p/>
     * 修改时间：无
     * <p/>
     * 修改备注：无
     *
     * @param key  String类型 键
     * @param type Class<T>类型 内容类型
     * @return T类型 返回结果
     */
    @Override
    public <T> T getCacheTData(String key, Class<T> type) throws AppDaoException {
        T rv = null;
        try {
            if (!StringUtils.isEmpty(key)) {
                final byte[] finalKey = key.getBytes("utf-8");
                byte[] value = jedisPool.getResource().get(finalKey);
                if (value == null) {
                    return null;
                }
                byte[] head = new byte[DEFALUTHEAD.length];
                System.arraycopy(value, 0, head, 0, DEFALUTHEAD.length);
                Object object = null;
                if (Arrays.equals(head, DEFALUTHEAD)) {
                    byte[] body = new byte[value.length - DEFALUTHEAD.length];
                    System.arraycopy(value, DEFALUTHEAD.length, body, 0,
                            value.length - DEFALUTHEAD.length);
                    body = AesUtil.decrypt(body);
                    object = SerializeUtil.unSerialize(body);
                } else {
                    object = SerializeUtil.unSerialize(value);
                }
                final Class<T> finalType = type;
                if (finalType != null && finalType.isInstance(object) && null != object) {
                    rv = (T) object;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {

        }
        return rv;
    }


    /**
     * 描述：清空缓存
     * <p/>
     * 创建时间：2017-03-23
     * <p/>
     * 创建人： 董耀明
     * <p/>
     * 修改人：无
     * <p/>
     * 修改时间：无
     * <p/>
     * 修改备注：无
     * <p/>
     * 版本：V1.0
     */
    @Override
    public void clear() throws AppDaoException {
        jedisPool.getResource().flushDB();
    }


    @Override
    public Collection<String> getKeys(String pattern) throws AppDaoException {
        return jedisPool.getResource().keys(pattern);
    }


    @Override
    public boolean deleteRegexCacheData(String pattern) throws AppDaoException {
        Jedis jedis = jedisPool.getResource();
        for (String key : jedis.keys(pattern)) {
            jedis.del(key);
        }
        return true;
    }


    @Override
    public void init(String dbIndex) {
        // TODO Auto-generated method stub
        logger.error("切换数据源下标到"+dbIndex);
    }
}
