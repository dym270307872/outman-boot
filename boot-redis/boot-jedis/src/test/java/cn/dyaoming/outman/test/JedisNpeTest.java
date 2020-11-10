package cn.dyaoming.outman.test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.dyaoming.utils.SerializeUtil;
import junit.BaseJunit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisNpeTest extends BaseJunit {

	@Autowired
	private JedisPool jedisPool;
	
	@Test
	public void run() throws Exception {
		Jedis jedis = jedisPool.getResource();
		byte[] cacheKey = "asdf".getBytes("utf-8");
		jedis.set(cacheKey, SerializeUtil.serialize(true));
		Boolean flag = (Boolean)SerializeUtil.unSerialize(jedis.get(cacheKey));
		if (flag) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
}
