package cn.dyaoming.boot.dao;

import cn.dyaoming.cache.interfaces.CacheInterface;

import cn.dyaoming.errors.AppDaoException;
import cn.dyaoming.utils.SerializeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.Collection;
import java.util.List;
import java.util.Map;
@Component("cacheDao")
public class CacheByJedisClusterImp implements CacheInterface
{


    private static final Logger logger  = LogManager.getLogger(CacheByJedisClusterImp.class);


    private  JedisCluster jedisCluster ;


    public CacheByJedisClusterImp()
    {

    }
    /**
     *
     * 功能描述：判断是否存在键值。
     * <P/>
     * 创建时间：2017-02-12
     * <P/>
     * 创建人： DYM
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     *
     * @param key String类型  键
     *
     * @return boolean类型  返回结果
     *
     */
	public boolean exists(String key) throws AppDaoException
    {
        boolean rv = false;

        try
        {
            if (null != key) {
                final String finalKey;
                if (key instanceof String) {
                    finalKey = key;
                } else {
                    finalKey = key.toString();
                }
                if (!org.springframework.util.StringUtils.isEmpty(finalKey)) {
                    Boolean aBoolean = jedisCluster.exists((finalKey).getBytes());
                    rv = aBoolean;
			/*		Object obj = redisTemplate.execute(new RedisCallback<Boolean>() {
						@Override
						public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
							return connection.exists((finalKey).getBytes());
						}
					});
					rv = (Boolean)obj;*/
                }
            }

        }catch(Exception e)
        {
            logger.error("异常：exists()方法出现异常，异常详细信息："+e.getMessage()+"。");
            throw new AppDaoException("判断缓存内容是否存在异常！",e);
        }

        return rv;
    }


    /**
     *
     * 功能描述：获取 keys。
     * <P/>
     * 创建时间：2017-02-12
     * <P/>
     * 创建人： DYM
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     *
     * @param pattern String类型  key的表达式，也可以使用通配符(*)
     *
     * @return Collection<String>类型  返回结果
     *
     */
/*    public Collection<String> getKeys(String pattern) throws AppDaoException
    {
        return redisTemplate.keys(pattern);
    }*/



    /**
     *
     * 功能描述：设置缓存 字符串类型数据。
     * <P/>
     * 创建时间：2017-02-12
     * <P/>
     * 创建人： DYM
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     *
     * @param key String类型  键
     *
     * @param value String类型  内容
     *
     * @return boolean类型  返回结果
     *
     */
/*    public boolean setCacheStringData(String key,String value) throws AppDaoException
    {
        boolean rv = false;

        try
        {
            if (org.springframework.util.StringUtils.isEmpty(key) || org.springframework.util.StringUtils.isEmpty(value)) {
                return false;
            } else {
                final String finalKey;
                if (key instanceof String) {
                    finalKey = key;
                } else {
                    finalKey = key.toString();
                }
                if (!org.springframework.util.StringUtils.isEmpty(finalKey)) {
                    final Object finalValue = value;
                    redisTemplate.opsForValue().set((finalKey).getBytes(), SerializeUtil.serialize(finalValue));
                    return true;
	        *//*        redisTemplate.execute(new RedisCallback<Boolean>() {
	                    @Override
	                    public Boolean doInRedis(RedisConnection connection) {
	                        connection.set((finalKey).getBytes(), SerializeUtil.serialize(finalValue));
	                        return true;
	                    }
	                });*//*
                }
            }

        }catch(Exception e)
        {
            logger.error("异常：setCacheStringData()方法出现异常，异常详细信息："+e.getMessage()+"。");
            throw new AppDaoException("缓存字符串类型内容出现异常！",e);
        }

        return rv;
    }*/



    /**
     *
     * 功能描述：设置缓存 字符串类型数据。
     * <P/>
     * 创建时间：2017-02-12
     * <P/>
     * 创建人： DYM
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     *
     * @param key String类型  键
     *
     * @param value String类型  内容
     * 位：毫
     * @param validTime long类型  有效时间（单位：秒）
     *
     * @return boolean类型  返回结果
     *
     */
	public boolean setCacheStringData(String key,String value,final long validTime) throws AppDaoException
    {
        boolean rv = false;

        try
        {
            if (org.springframework.util.StringUtils.isEmpty(key) || org.springframework.util.StringUtils.isEmpty(value)) {
                return false;
            } else {
                final String finalKey;
                if (key instanceof String) {
                    finalKey = key;
                } else {
                    finalKey = key.toString();
                }
                if (!org.springframework.util.StringUtils.isEmpty(finalKey)) {
                    final Object finalValue = value;
                    //jedisCluster.set(key, value, String.valueOf(validTime));
                    jedisCluster.set((finalKey).getBytes(), SerializeUtil.serialize(finalValue));
                    // 设置超时间
                    jedisCluster.expire((finalKey).getBytes(), (int) validTime);
                    return true;

		/*			redisTemplate.execute(new RedisCallback<Boolean>() {
	                    @Override
	                    public Boolean doInRedis(RedisConnection connection) {
	                        connection.set((finalKey).getBytes(), SerializeUtil.serialize(finalValue));
	                        // 设置超时间
	                        connection.expire((finalKey).getBytes(), validTime);
	                        return true;
	                    }
	                });*/
                }
            }

        }catch(Exception e)
        {
            logger.error("异常：setCacheStringData()方法出现异常，异常详细信息："+e.getMessage()+"。");
            throw new AppDaoException("缓存字符串类型内容出现异常！",e);
        }

        return rv;
    }




    /**
     *
     * 功能描述：设置缓存对象类型内容。
     * <P/>
     * 创建时间：2017-02-12
     * <P/>
     * 创建人： DYM
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     *
     * @param key String类型  键
     *
     * @param value Object类型  内容
     *
     * @return boolean类型  返回结果
     *
     */
	public boolean setCacheObjectData(String key,Object value) throws AppDaoException
    {
        boolean rv = false;

        try
        {
            if (org.springframework.util.StringUtils.isEmpty(key) || value == null) {
                return false;
            } else {
                final String finalKey;
                if (key instanceof String) {
                    finalKey = key;
                } else {
                    finalKey = key.toString();
                }
                if (!org.springframework.util.StringUtils.isEmpty(finalKey)) {
                    final Object finalValue = value;
                    //jedisCluster.set(finalKey,value.toString());
                    jedisCluster.set((finalKey).getBytes(), SerializeUtil.serialize(finalValue));

                    return true;
/*	                redisTemplate.execute(new RedisCallback<Boolean>() {
	                    @Override
	                    public Boolean doInRedis(RedisConnection connection) {
	                        connection.set((finalKey).getBytes(), SerializeUtil.serialize(finalValue));
	                        return true;
	                    }
	                });*/
                }
            }

        }catch(Exception e)
        {
            logger.error("异常：setCacheObjectData()方法出现异常，异常详细信息："+e.getMessage()+"。");
            throw new AppDaoException("缓存对象类型内容出现异常！",e);
        }

        return rv;
    }



    /**
     *
     * 功能描述：设置缓存对象类型内容。
     * <P/>
     * 创建时间：2017-02-12
     * <P/>
     * 创建人： DYM
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     *
     * @param key String类型  键
     *
     * @param value Object类型  内容
     *
     * @param validTime long类型  有效时间（单位：秒）
     *
     * @return boolean类型  返回结果
     *
     */
	public boolean setCacheObjectData(String key,Object value,final long validTime) throws AppDaoException
    {
        boolean rv = false;

        try
        {
            if (org.springframework.util.StringUtils.isEmpty(key) || value == null) {
                return false;
            } else {
                final String finalKey;
                if (key instanceof String) {
                    finalKey = key;
                } else {
                    finalKey = key.toString();
                }
                if (!org.springframework.util.StringUtils.isEmpty(finalKey)) {
                    final Object finalValue = value;
                  //  jedisCluster.set(finalKey,value.toString(), String.valueOf(validTime));
                    jedisCluster.set((finalKey).getBytes(), SerializeUtil.serialize(finalValue));
                    // 设置超时间
                    jedisCluster.expire((finalKey).getBytes(), (int) validTime);
                    return true;

	       /*         redisTemplate.execute(new RedisCallback<Boolean>() {
	                    @Override
	                    public Boolean doInRedis(RedisConnection connection) {
	                        connection.set((finalKey).getBytes(), SerializeUtil.serialize(finalValue));
	                        // 设置超时间
	                        connection.expire((finalKey).getBytes(), validTime);
	                        return true;
	                    }
	                });*/
                }
            }

        }catch(Exception e)
        {
            logger.error("异常：setCacheObjectData()方法出现异常，异常详细信息："+e.getMessage()+"。");
            throw new AppDaoException("缓存对象类型内容出现异常！",e);
        }

        return rv;
    }




    /**
     *
     * 功能描述：删除缓存内容。
     * <P/>
     * 创建时间：2017-02-12
     * <P/>
     * 创建人： DYM
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     *
     * @param key String类型  键
     *
     * @return boolean类型  返回结果
     *
     */
	public boolean deleteCacheData(String key) throws AppDaoException
    {
        boolean rv = false;

        try
        {
            if (null != key) {
                final String finalKey;
                if (key instanceof String) {
                    finalKey = key;
                } else {
                    finalKey = key.toString();
                }
                if (!org.springframework.util.StringUtils.isEmpty(finalKey)) {
                    jedisCluster.del((finalKey).getBytes());
                    return true;
				/*	redisTemplate.execute(new RedisCallback<Long>() {
						@Override
						public Long doInRedis(RedisConnection connection) throws DataAccessException {
							return connection.del((finalKey).getBytes());
						}
					});*/


                }
            }

        }catch(Exception e)
        {
            logger.error("异常：deleteCacheData()方法出现异常，异常详细信息："+e.getMessage()+"。");
            throw new AppDaoException("删除缓存内容出现异常！",e);
        }

        return rv;
    }



    /**
     *
     * 功能描述：使用通配符批量删除缓存内容。
     * <P/>
     * 创建时间：2017-02-12
     * <P/>
     * 创建人： DYM
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     *
     * @param keyPrefix String类型  键前缀
     *
     * @param symbol String类型  符号
     *
     * @return boolean类型  返回结果
     *
     */
/*    public boolean deleteCacheData(String keyPrefix, String symbol) throws AppDaoException
    {
        boolean rv = false;

        try
        {
            //使用通配符 进行 查询所有Key
            Set<String> keys = redisTemplate.keys(keyPrefix + symbol);

            redisTemplate.delete(keys);

            rv = true;

        }catch(Exception e)
        {
            logger.error("异常：deleteCacheData()方法出现异常，异常详细信息："+e.getMessage()+"。");
            throw new AppDaoException("删除缓存内容出现异常！",e);
        }

        return rv;
    }*/



    /**
     *
     * 功能描述：批量删除缓存内容。
     * <P/>
     * 创建时间：2017-02-12
     * <P/>
     * 创建人： DYM
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     *
     * @param key List<String>类型  键
     *
     * @return boolean类型  返回结果
     *
     */
 /*   public boolean deleteCacheData(List<String> key) throws AppDaoException
    {
        boolean rv = false;

        try
        {
            redisTemplate.delete(key);

            rv = true;

        }catch(Exception e)
        {
            logger.error("异常：deleteCacheData()方法出现异常，异常详细信息："+e.getMessage()+"。");
            throw new AppDaoException("删除缓存内容出现异常！",e);
        }

        return rv;
    }*/



    /**
     *
     * 功能描述：获取缓存内容。
     * <P/>
     * 创建时间：2017-02-12
     * <P/>
     * 创建人： DYM
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     *
     * @param key String类型  键
     *
     * @return Object类型  返回结果
     *
     */
	public Object getCacheData(String key) throws AppDaoException
    {
        Object rv = null;

        try
        {
            if (org.springframework.util.StringUtils.isEmpty(key)) {
                return null;
            } else {
                final String finalKey;
                if (key instanceof String) {
                    finalKey = key;
                } else {
                    finalKey = key.toString();
                }
                //Object object = jedisCluster.get(finalKey);

                byte[] value = jedisCluster.get((finalKey).getBytes());
                if (value == null) {
                    return null;
                }
                Object  object  = SerializeUtil.unSerialize(value);
                /*byte[] key1 = (finalKey).getBytes();
                Object object = redisTemplate.opsForValue().get(key1);*///根据key获取缓存中的val

			/*	final Object object = redisTemplate.execute(new RedisCallback<Object>() {
	                @Override
					public Object doInRedis(RedisConnection connection) throws DataAccessException {
	                    byte[] key = (finalKey).getBytes();
	                    byte[] value = connection.get(key);
	                    if (value == null) {
	                        return null;
	                    }
	                    return SerializeUtil.unSerialize(value);
	                }
	            });*/
                rv = object;

            }

        }catch(Exception e)
        {
            logger.error("异常：getCacheData()方法出现异常，异常详细信息："+e.getMessage()+"。");
            throw new AppDaoException("获取缓存内容出现异常！",e);
        }

        return rv;
    }



    @Override
    public boolean exists(Object key) throws AppDaoException {
        return false;
    }



    @Override
    public boolean setCacheObjectData(Object key, Object value) throws AppDaoException {
        return false;
    }



    @Override
    public boolean setCacheObjectData(Object key, Object value, long validTime)
            throws AppDaoException {
        return false;
    }



    @Override
    public boolean setCacheObjectData(Object key, Object value, boolean secret)
            throws AppDaoException {
        return false;
    }



    @Override
    public boolean setCacheObjectData(Object key, Object value, long validTime, boolean secret)
            throws AppDaoException {
        return false;
    }



    @Override
    public boolean deleteCacheData(Object key) throws AppDaoException {
        return false;
    }



    @Override
    public Object getCacheData(Object key) throws AppDaoException {
        return null;
    }



    /**
     *
     * 功能描述：获取缓存内容。
     * <P/>
     * 创建时间：2017-02-12
     * <P/>
     * 创建人： DYM
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     *
     * @param key String类型  键
     *
     * @param type Class<T>类型  内容类型
     *
     * @return T类型  返回结果
     *
     */
    @Override
	public <T> T getCacheTData(String key,Class<T> type) throws AppDaoException
    {
        if (org.springframework.util.StringUtils.isEmpty(key) || null == type) {
            return null;
        } else {
            final String finalKey;
            final Class<T> finalType = type;
            if (key instanceof String) {
                finalKey = key;
            } else {
                finalKey = key.toString();
            }

            byte[] value = jedisCluster.get((finalKey).getBytes());
            if (value == null) {
                return null;
            }
            Object object =   SerializeUtil.unSerialize(value);
            /*byte[] key1 = (finalKey).getBytes();
            Object object = redisTemplate.opsForValue().get(key1);*/
            //根据key获取缓存中的val

       /*     final Object object = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] key = (finalKey).getBytes();
                    byte[] value = connection.get(key);
                    if (value == null) {
                        return null;
                    }
                    return SerializeUtil.unSerialize(value);
                }
            });*/
            if (finalType != null && finalType.isInstance(object) && null != object) {
                return (T) object;
            } else {
                return null;
            }
        }
    }


    /**
     * 描述： redis设置Map
     * <P/>
     * 创建时间：2017-03-23
     * <P/>
     * 创建人： 董耀明
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     * <P/>
     * 版本：V1.0
     *
     */
	public boolean setMap(String redisKey,final Map<String, String> args)
    {
        boolean rv = false;

        try
        {
            if (org.springframework.util.StringUtils.isEmpty(redisKey)) {
                return false;
            } else {
                final String finalKey;
                if (redisKey instanceof String) {
                    finalKey = redisKey;
                } else {
                    finalKey = redisKey.toString();
                }
              //  jedisCluster.hmset(finalKey, args);
                jedisCluster.set((finalKey).getBytes(), SerializeUtil.serialize(args));
              //  HashOperations<String, String, String> hash = jedisCluster.hmset(finalKey, args);
              //  hash.putAll(finalKey, args);
              /*  HashOperations<String, String, String> hash = redisTemplate.opsForHash();
                hash.putAll(finalKey, args);*/
                rv =true;
            }

        }catch(Exception e)
        {
            logger.error("异常：setMap()方法出现异常，异常详细信息："+e.getMessage()+"。");
            throw new AppDaoException("redis设置Map出现异常！",e);
        }

        return rv;
    }

    /**
     * 描述： 从redis缓存中查询Map
     * <P/>
     * 创建时间：2017-03-23
     * <P/>
     * 创建人： 董耀明
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     * <P/>
     * 版本：V1.0
     *
     */
	public Map<String,String> getMap(String redisKey)
    {
        Map<String,String> result = null;
        try
        {
            if (org.springframework.util.StringUtils.isEmpty(redisKey)) {
                return null;
            } else {
                String s = jedisCluster.get(redisKey);
//                result =  JsonUtil.parseObject(s,Map.class);
    /*            HashOperations<String, String, String> hash = redisTemplate.opsForHash();
                result = hash.entries(redisKey);*/
            }
        }catch(Exception e)
        {
            logger.error("异常：getMap()方法出现异常，异常详细信息："+e.getMessage()+"。");
            throw new AppDaoException("从redis缓存中查询Map出现异常！",e);
        }
        return result;
    }


    /**
     * 描述： 获取redis中mapKey对应value
     * <P/>
     * 创建时间：2017-03-23
     * <P/>
     * 创建人： 董耀明
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     * <P/>
     * 版本：V1.0
     *
     */
/*    public String getFromMap(String redisKey,String mapKey)
    {
        String result = null;
        try
        {
            if (org.springframework.util.StringUtils.isEmpty(redisKey)) {
                return null;
            } else {

                HashOperations<String, String, String> hash = redisTemplate.opsForHash();
                result = hash.get(redisKey,mapKey);
            }
        }catch(Exception e)
        {
            logger.error("异常：getFromMap()方法出现异常，异常详细信息："+e.getMessage()+"。");
            throw new AppDaoException("获取redis中mapKey对应value出现异常！",e);
        }
        return result;
    }*/



    /**
     * 描述： 向map中存放key-value
     * <P/>
     * 创建时间：2017-03-23
     * <P/>
     * 创建人： 董耀明
     * <P/>
     * 修改人：无
     * <P/>
     * 修改时间：无
     * <P/>
     * 修改备注：无
     * <P/>
     * 版本：V1.0
     *
     */
	public void putToMap(String redisKey,String key,String value)
    {
        try
        {
            if (!org.springframework.util.StringUtils.isEmpty(redisKey)) {

                byte[] values = jedisCluster.get((redisKey).getBytes());
                if (values == null) {
                  return;
                }
                Map<String,String>  object  = (Map) SerializeUtil.unSerialize(values);
                object.put(key,value);
                jedisCluster.set((redisKey).getBytes(), SerializeUtil.serialize(object));

                /*String s = jedisCluster.get(redisKey);
                Map map = JsonUtils.parseObject(s, Map.class);
                redisKey.put(key,value);
                jedisCluster.set(redisKey,JsonUtils.toJSONString(map));*/
                /*HashOperations<String, String, String> hash = redisTemplate.opsForHash();
                hash.put(redisKey,key,value);*/
            }
        }catch(Exception e)
        {
            logger.error("异常：putToMap()方法出现异常，异常详细信息："+e.getMessage()+"。");
            throw new AppDaoException("向map中存放key-value内容出现异常！",e);
        }
    }


    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }
    
    
    
	@Override
	public Collection<String> getKeys(String pattern) throws AppDaoException {
		// TODO Auto-generated method stub
		return null;
	}



    @Override
    public boolean deleteRegexCacheData(String pattern) throws AppDaoException {
        return false;
    }




	public boolean setCacheStringData(String key, String value) throws AppDaoException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteCacheData(String keyPrefix, String symbol) throws AppDaoException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteCacheData(List<String> key) throws AppDaoException {
		// TODO Auto-generated method stub
		return false;
	}

	public String getFromMap(String redisKey, String mapKey) throws AppDaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public void clear() throws AppDaoException {
		// TODO Auto-generated method stub
		
	}
}
