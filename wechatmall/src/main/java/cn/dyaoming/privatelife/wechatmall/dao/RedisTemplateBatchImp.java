package cn.dyaoming.privatelife.wechatmall.dao;/*package cn.dyaoming.dao;


import cn.dyaoming.errors.AppDaoException;
import cn.dyaoming.interfaces.CacheBatchInterface;
import cn.dyaoming.interfaces.CacheInterface;
import cn.dyaoming.utils.SerializeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


*//**
 * 类名称：RedisTemplateImp
 * <p/>
 * 类描述： 一个通过Spring RedisTemplate 处理数据缓存的基础接口 实现类。
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
 * <p/>
 * 版本：V1.0
 *//*

//@Component("cacheDao")
public class RedisTemplateBatchImp implements CacheBatchInterface {

	private static final Logger logger = LogManager.getLogger(RedisTemplateBatchImp.class);

	@Autowired
	private RedisTemplate redisTemplate;




	*//**
	 * 功能描述：获取 keys。
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
	 * @param pattern String类型 key的表达式，也可以使用通配符(*)
	 * @return Collection<String>类型 返回结果
	 *//*

	@Override
	public Collection<String> getKeys(String pattern) throws AppDaoException {
		return redisTemplate.keys(pattern);
	}






	*//**
	 * 功能描述：使用通配符批量删除缓存内容。
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
	 * @param keyPrefix String类型 键前缀
	 * @param symbol    String类型 符号
	 * @return boolean类型 返回结果
	 *//*

	@Override
	public boolean deleteCacheData(String keyPrefix, String symbol) throws AppDaoException {
		boolean rv = false;

		try {
			// 使用通配符 进行 查询所有Key
			Set<String> keys = redisTemplate.keys(keyPrefix + symbol);

			redisTemplate.delete(keys);

			rv = true;

		} catch(Exception e) {
			logger.error("异常：deleteCacheData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
			throw new AppDaoException("删除缓存内容出现异常！", e);
		}

		return rv;
	}



	*//**
	 * 功能描述：批量删除缓存内容。
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
	 * @param key List<String>类型 键
	 * @return boolean类型 返回结果
	 *//*

	@Override
	public boolean deleteCacheData(List<String> key) throws AppDaoException {
		boolean rv = false;

		try {
			redisTemplate.delete(key);

			rv = true;

		} catch(Exception e) {
			logger.error("异常：deleteCacheData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
			throw new AppDaoException("删除缓存内容出现异常！", e);
		}

		return rv;
	}







	*//**
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
	 *//*

	@Override
	public <T> T getCacheTData(String key, Class<T> type) throws AppDaoException {
		if (StringUtils.isEmpty(key) || null == type) {
			return null;
		} else {
			final String finalKey;
			final Class<T> finalType = type;
			if (key instanceof String) {
				finalKey = key;
			} else {
				finalKey = key.toString();
			}
			final Object object = redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
					byte[] key = (finalKey).getBytes();
					byte[] value = connection.get(key);
					if (value == null) {
						return null;
					}
					return SerializeUtil.unSerialize(value);
				}
			});
			if (finalType != null && finalType.isInstance(object) && null != object) {
				return (T) object;
			} else {
				return null;
			}
		}
	}



	*//**
	 * 描述： redis设置Map
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
	 *//*

	@Override
	public boolean setMap(String redisKey, final Map<String, String> args) {
		boolean rv = false;

		try {
			if (StringUtils.isEmpty(redisKey)) {
				return false;
			} else {
				final String finalKey;
				if (redisKey instanceof String) {
					finalKey = redisKey;
				} else {
					finalKey = redisKey.toString();
				}
				HashOperations<String, String, String> hash = redisTemplate.opsForHash();
				hash.putAll(finalKey, args);
			}

		} catch(Exception e) {
			logger.error("异常：setMap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
			throw new AppDaoException("redis设置Map出现异常！", e);
		}

		return rv;
	}



	*//**
	 * 描述： 从redis缓存中查询Map
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
	 *//*

	@Override
	public Map<String, String> getMap(String redisKey) {
		Map<String, String> result = null;
		try {
			if (StringUtils.isEmpty(redisKey)) {
				return null;
			} else {

				HashOperations<String, String, String> hash = redisTemplate.opsForHash();
				result = hash.entries(redisKey);
			}
		} catch(Exception e) {
			logger.error("异常：getMap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
			throw new AppDaoException("从redis缓存中查询Map出现异常！", e);
		}
		return result;
	}



	*//**
	 * 描述： 获取redis中mapKey对应value
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
	 *//*

	@Override
	public String getFromMap(String redisKey, String mapKey) {
		String result = null;
		try {
			if (StringUtils.isEmpty(redisKey)) {
				return null;
			} else {

				HashOperations<String, String, String> hash = redisTemplate.opsForHash();
				result = hash.get(redisKey, mapKey);
			}
		} catch(Exception e) {
			logger.error("异常：getFromMap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
			throw new AppDaoException("获取redis中mapKey对应value出现异常！", e);
		}
		return result;
	}



	*//**
	 * 描述： 向map中存放key-value
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
	 *//*

	@Override
	public void putToMap(String redisKey, String key, String value) {
		try {
			if (!StringUtils.isEmpty(redisKey)) {
				HashOperations<String, String, String> hash = redisTemplate.opsForHash();
				hash.put(redisKey, key, value);
			}
		} catch(Exception e) {
			logger.error("异常：putToMap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
			throw new AppDaoException("向map中存放key-value内容出现异常！", e);
		}
	}




	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}



	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}


*/