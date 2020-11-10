package cn.dyaoming.outman.test;

import junit.BaseJunit;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.dyaoming.utils.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;

public class JedisBatchRunTest extends BaseJunit {

	@Autowired
	private JedisPool jedisPool;

	@Test
	public void testJedisBatch() {
		Jedis jedis = jedisPool.getResource();

		String cacheKey = "hello";
		String[] list = new String[] { "dyaoming", "dyaoming1", "dyaoming2", "dyaoming3", "dyaoming4", "dyaoming5" };

		//初始化队列
		System.out.println(jedis.exists(cacheKey));
		System.out.println(jedis.rpush(cacheKey, list));
		System.out.println(jedis.exists(cacheKey));
		
		//追加队列
		String[] list2 = new String[] { "dyaoming8", "dyaoming7", "dyaoming6" };

		System.out.println(jedis.rpush(cacheKey, list2));
		
		List<String> result = jedis.sort(cacheKey, new SortingParams().alpha().desc());
		for(String value:result) {
			System.out.println("hello " + value);
		}
		
		while (jedis.exists(cacheKey)) {
			System.out.println("hello " + jedis.lpop(cacheKey));
		}

		
		jedis.close();
	}
	
	
	
	@Test
	public void testJedisObjectBatch() throws Exception {
		Jedis jedis = jedisPool.getResource();

		String cacheKey = "hello";
		Object[] list = new Object[] { "dyaoming", "dyaoming1", "dyaoming7", "dyaoming5", "dyaoming2", "dyaoming6" };
		byte[][] newList = new byte[list.length][];
		
		for(int i=0;i<list.length;i++) {
			newList[i] = SerializeUtil.serialize(list[i]);
		}
		
		//初始化队列
		System.out.println(jedis.exists(cacheKey));
		System.out.println(jedis.rpush(cacheKey.getBytes("utf-8"), newList));
		System.out.println(jedis.exists(cacheKey));
		
//		//追加队列
//		String[] list2 = new String[] { "dyaoming8", "dyaoming7", "dyaoming6" };
//
//		System.out.println(jedis.rpush(cacheKey, list2));
		
		List<byte[]> result = jedis.sort(cacheKey.getBytes("utf-8"), new SortingParams().alpha().desc());
		for(byte[] value:result) {
			System.out.println("hello " + SerializeUtil.unSerialize(value));
		}
//		jedis.lpop
//		while (jedis.exists(cacheKey)) {
//			System.out.println("hello " + jedis.lpop(cacheKey));
//		}

		
		jedis.close();
	}
}
