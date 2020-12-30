package cn.dyaoming.outman.test;

import junit.BaseJunit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisRun extends BaseJunit {

	@Autowired
	private JedisPool jedisPool;

	@Test
	public void testJedis() {

		Jedis jedis = jedisPool.getResource();
		try {
			jedis.flushDB();
			System.out.println(jedis.exists("hello董耀明"));
			System.out.println(jedis.set("hello董耀明".getBytes(), "hello".getBytes()));
			System.out.println(jedis.exists("hello董耀明"));
			System.out.println(jedis.setnx("hello董耀明".getBytes("utf-8"), "hello1".getBytes("utf-8")));
			System.out.println(jedis.exists("hello董耀明".getBytes("GBK")));
			System.out.println(jedis.get("hello董耀明".getBytes("utf-8")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
